import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    private final YouTube youTube;

    public HomeController(YouTube youTube) {
        this.youTube = youTube;
    }

    @GetMapping("/")
    String index(Model model) {
        model.channelVideos("UCjukbYOd6pjrMpNMFAOKYyw", 10 YouTube.Sort.VIEW_COUNT);
        return "index";
    }

    
}
