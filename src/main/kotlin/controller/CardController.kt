package controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import service.CardService

@Controller
class CardController(@Autowired cardService : CardService) {

    @GetMapping("/card")
    fun getCard () {
        return
    }
}