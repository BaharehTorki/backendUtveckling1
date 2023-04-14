package com.example.spring_assignments.LuckyYou;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class LuckyYouController {

    //1a:
    public String randomNumber() {
        return Integer.toString((int) (Math.random() * 10) + 1);
    }

    public String RandomAnimal() {
        List<String> animalList = List.of("hund", "katt", "spindel", "skata");
        return animalList.get((int) (Math.random() * 4));
    }

    //1e metod:
    public boolean checkIfLuckyNumber(String number, List<String> listOfNumbers) {
        boolean lucky = true;
        for (String numberOnList : listOfNumbers) {
            if (numberOnList.equals(number)) {
                lucky = false;
                break;
            }
        }
        return lucky;
    }

    //1f metod:
    public List<String> giveMeMyLuckyNumbers(List<String> listOfNumbers) {
        List<String> listOfLuckyNumbers = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            if (!listOfNumbers.contains(Integer.toString(i))) {
                listOfLuckyNumbers.add(Integer.toString(i));
            }
        }
        return listOfLuckyNumbers;
    }

    //1c:
    @RequestMapping("/random")
    public String helloRandom(@RequestParam String luckyYou, @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName) {
        String randomThing = "";
        if (Objects.equals(luckyYou, "animal")) {
            if (firstName != null && lastName != null) {
                randomThing = firstName + " " + lastName + ", ditt lyckodjur är " + RandomAnimal();
            }
        } else if (Objects.equals(luckyYou, "number")) {
            if (firstName != null && lastName != null) {
                randomThing = firstName + " " + lastName + ", ditt lyckonummer är " + randomNumber();
            } else if (firstName != null) {
                randomThing = firstName + ", ditt lyckonummer är " + randomNumber();
            } else if (lastName != null) {
                randomThing = lastName + ", ditt lyckonummer är " + randomNumber();
            } else {
                randomThing = "Ditt lyckonummer är " + randomNumber();
            }
        } else {
            randomThing = "Något är fel";
        }
        return randomThing;
    }


    //1d:
    @RequestMapping("/randomDefault")
    public String helloRandomDefault(@RequestParam String luckyYou, @RequestParam(defaultValue = "Eli") String firstName,
                                     @RequestParam(defaultValue = "Ku") String lastName) {
        String randomThing = "";
        if (Objects.equals(luckyYou, "animal")) {
            if ((firstName != null) || (lastName != null) || (firstName.isEmpty()) || (lastName.isEmpty()) || (firstName.isBlank()) || (lastName.isBlank())) {
                randomThing = firstName + " " + lastName + ", ditt lyckodjur är " + RandomAnimal();
            }
        } else if (Objects.equals(luckyYou, "number")) {
            if (firstName != null && lastName != null) {
                randomThing = firstName + " " + lastName + ", ditt lyckonummer är " + randomNumber();
            } else if (firstName != null) {
                randomThing = firstName + ", ditt lyckonummer är " + randomNumber();
            } else if (lastName != null) {
                randomThing = lastName + ", ditt lyckonummer är " + randomNumber();
            }
        } else {
            randomThing = "Något är fel";
        }
        return randomThing;
    }

    //1e:
    //http://localhost:8080/randomListNumbers?listOfUnluckyNumbers=1,2,3,4,5
    @RequestMapping("/randomListNumbers")
    public String helloRandomListNumbers(@RequestParam List<String> listOfUnluckyNumbers) {
        String randomNumber = randomNumber();
        boolean drawAgain = true;
        while (drawAgain) {
            if (checkIfLuckyNumber(randomNumber, listOfUnluckyNumbers)) {
                drawAgain = false;
            } else {
                randomNumber = randomNumber();
            }
        }
        return "Ditt lyckonummer är " + randomNumber;
    }


    @RequestMapping("/randomListNumbersPossible")
    public String helloRandomListNumbersPossible(@RequestParam List<String> listOfUnluckyNumbers) {
        return "Ditt/dina lyckonummer är " + giveMeMyLuckyNumbers(listOfUnluckyNumbers);
    }

    //1g:
    //http://localhost:8080/randomPath/number
    //http://localhost:8080/randomPath/animal
    @RequestMapping("/randomPath/{luckyYou}")
    public String helloRandomPath(@PathVariable String luckyYou) {
        String randomThing = "";
        if (Objects.equals(luckyYou, "animal")) {
            randomThing = "Ditt lyckodjur är " + RandomAnimal();
        } else if (Objects.equals(luckyYou, "number")) {
            randomThing = "Ditt lyckonummer är " + randomNumber();
        }
        return randomThing;
    }

    //1h:
    @RequestMapping("/randomHTML")
    public String helloRandomHTML(@RequestParam String luckyYou) {
        String randomThing = "";
        if (Objects.equals(luckyYou, "animal")) {
            randomThing = "<HTML><HEAD><TITLE>Hello</TITLE></HEAD><BODY><h1>Ditt lyckodjur är " + RandomAnimal() + "</h1></BODY></HTML>";
        } else if (Objects.equals(luckyYou, "number")) {
            randomThing = "<HTML><HEAD><TITLE>Hello</TITLE></HEAD><BODY><h1>Ditt lyckodjur är " + randomNumber() + "</h1></BODY></HTML>";
        }
        return randomThing;
    }
}

