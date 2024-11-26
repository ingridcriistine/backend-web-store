import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repositories.UserRepository;

@RestController
@RequestMapping()
pulic class UserController(){

    @Autowired
    UserRepository repo;

    
}