package group.service.iko;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Test {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder();
        String encoded = encoder.encode("ikocoordinator");
        System.out.println(encoded);

    }
}
