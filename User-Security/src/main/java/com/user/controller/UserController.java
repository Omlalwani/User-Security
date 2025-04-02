package com.user.controller;

import com.user.DTO.PlayerDTO;
import com.user.apis.COCResponse;
import com.user.entity.User;
import com.user.service.JwtService;
import com.user.service.PlayerService;
import com.user.service.serviceImpl.UserServiceImpl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserServiceImpl service;

    @Autowired
    JwtService jwtService;

    @Autowired
    PlayerService playerService;

    @RequestMapping("/home")
    public String home()
    {
        return "Hello, World...";
    }

    @PostMapping("/register")
    public User save(@RequestBody User user)
    {
        return service.saveUser(user);
    }

    @GetMapping("/login")
    public String login(@RequestBody User user)
    {
        String token = jwtService.generateKey(user.getName());
        return service.loginUser(user) + " : " + token;
    }

    @GetMapping("/checkUser")
    public ResponseEntity<?> getUser()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("Hey! " + auth.getName(), HttpStatus.OK);
    }

    @GetMapping("/player/{playerTag}")
    public ResponseEntity<PlayerDTO> getPlayerData(@PathVariable String playerTag)
    {
        System.out.println("Received playerTag: " + playerTag);
        COCResponse cocResponse = playerService.getPlayerData(playerTag);
        PlayerDTO playerDTO = playerService.playerDTO(cocResponse);

        return ResponseEntity.ok(playerDTO); //Returns that data which we have to show, instead of Full response from the api.
    }

    @GetMapping(value = "/player/{playerTag}/chart", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generatePlayerChart(@PathVariable String playerTag){
        COCResponse cocResponse = playerService.getPlayerData(playerTag);
        PlayerDTO player = playerService.playerDTO(cocResponse);


        //Creates Dataset for the Chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(player.getTownHallLevel(), "Stats", "Town Hall");
        dataset.addValue(player.getCurrentTrophies(), "Stats", "Trophies");
        dataset.addValue(player.getBestTrophies(), "Stats", "Best Trophies");
        dataset.addValue(player.getWarStars(), "Stats", "War Stars");


        //Generates The Chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Player Stats : " + player.getPlayerName(),
                "Category",
                "Value",
                dataset
        );

        //Converts chart to PNG
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream())
        {
            ChartUtils.writeChartAsPNG(baos, chart, 800, 600);
            byte[] imageBytes = baos.toByteArray();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentDisposition(
                    ContentDisposition.attachment()
                            .filename("player_stats.png")
                            .build()
            );

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
