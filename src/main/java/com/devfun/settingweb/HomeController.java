package com.devfun.settingweb;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devfun.vo.MovieVO;
import com.devfun.service.MovieService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Inject
    private MovieService service;

    /**
     * Simply selects the home view to render by returning its name.
     */
    @RequestMapping(value = "/theater", method = RequestMethod.GET)
    public String home(Locale locale, Model model) throws Exception{

        logger.info("home");
        System.out.println("=======================");   /////

        List<MovieVO> movieList = service.selectMovie();

        model.addAttribute("movieList", movieList);

        return "home";
    }

}