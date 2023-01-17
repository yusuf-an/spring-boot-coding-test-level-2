package com.accenture.codingtest.springbootcodingtest;



import com.accenture.codingtest.springbootcodingtest.entity.Project;
import com.accenture.codingtest.springbootcodingtest.entity.Task;
import com.accenture.codingtest.springbootcodingtest.entity.User;
import com.accenture.codingtest.springbootcodingtest.enums.ROLES_ENUMS;
import com.accenture.codingtest.springbootcodingtest.service.ProjectService;
import com.accenture.codingtest.springbootcodingtest.service.TaskService;
import com.accenture.codingtest.springbootcodingtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataSeeder {
    Logger LOG = LoggerFactory.getLogger(DataSeeder.class);
    @Autowired
    UserService userService;

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @PostConstruct
    public void createInitialData(){
        User user=new User();
        user.setPassword("admin");
        user.setUsername("admin");
        user.setRole(ROLES_ENUMS.ADMIN);
        userService.save(user);
        LOG.info("created admin: "+user);

        User productOwner=new User();
        productOwner.setPassword("productOwner");
        productOwner.setUsername("productOwner");
        productOwner.setRole(ROLES_ENUMS.PRODUCT_OWNER);
        userService.save(productOwner);
        LOG.info("created product owner: "+productOwner);

        User teamMember=new User();
        teamMember.setPassword("teamMember");
        teamMember.setUsername("teamMember");
        teamMember.setRole(ROLES_ENUMS.DEFAULT_USER);
        userService.save(teamMember);
        LOG.info("created teammeber: "+teamMember);


        Project project =new Project();
        project.setName("Sample Project");
        projectService.save(project);
        LOG.info("created project: "+project);

        Task task=new Task();
        task.setUser(teamMember);
        task.setProject(project);
        task.setDescription("sample description for task");
        task.setTitle("sample title");
        taskService.save(task);

        LOG.info("created task: "+task);
    }
}
