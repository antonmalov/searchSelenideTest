package github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenideSearch {
    private final String URL_GITHUB = "https://github.com/";
    private final String URL_SELENIDE = "https://github.com/selenide/selenide";

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldFindSelenideRepository() {
        open(URL_GITHUB);
        $("[placeholder='Search GitHub']").setValue("Selenide").pressEnter();
        $$("ul.repo-list li").first().$("a").click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }

    @Test
    void shouldFindTopContributors() {
        open(URL_SELENIDE);
        $("div.Layout-sidebar").$(byText("Contributors"))
                .closest(".BorderGrid-cell").$$("ul li").first().hover();
        $$(".Popover").findBy(visible).shouldHave(text("Andrei Solntsev"));
    }


}
