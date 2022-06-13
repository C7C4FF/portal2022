package kr.ac.jejunu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    private final UserDao userDao;

    @GetMapping
    public User get(@RequestParam("id") Integer id, @RequestParam("name") String name){
        return userDao.findById(id).get();
    }

    @PostMapping
    public User create(@RequestBody User user){
        userDao.save(user);
        return user;
    }

    @PutMapping
    public User modify(@RequestBody User user){
        user.setName("modified");
        return user;
    }

    @DeleteMapping
    public void delete(@RequestParam Integer id){

    }
}
