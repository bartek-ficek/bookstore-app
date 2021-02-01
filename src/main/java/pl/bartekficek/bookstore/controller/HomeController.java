package pl.bartekficek.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.bartekficek.bookstore.domain.User;
import pl.bartekficek.bookstore.domain.security.PasswordResetToken;
import pl.bartekficek.bookstore.domain.security.Role;
import pl.bartekficek.bookstore.domain.security.UserRole;
import pl.bartekficek.bookstore.service.UserService;
import pl.bartekficek.bookstore.service.impl.UserSecurityService;
import pl.bartekficek.bookstore.utility.SecurityUtility;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/forgetPassword")
    public String forgetPassword(Model model) {
        model.addAttribute("classActiveForgetPassword", true);
        return "myAccount";
    }

    @PostMapping(value = "/newUser")
    public String newUserPost(HttpServletRequest request, @ModelAttribute("email") String userEmail, @ModelAttribute("username") String username, Model model) {
        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", username);

        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameExist", true);
            return "myAccount";
        }

        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("userEmailExist", true);
            return "myAccount";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

    }

    @RequestMapping("/newUser")
    public String newUser(Locale locale, @RequestParam("token") String token, Model model) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);

        if (passToken == null) {
            String message = "Invalid Token";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }

        User user = passToken.getUser();
        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("classActiveEdit", true);
        return "myProfile";
    }

}
