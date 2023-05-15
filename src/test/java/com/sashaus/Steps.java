package com.sashaus;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class Steps {

    @Step("Открыть главную страницу github")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Найти репозиторий {0}")
    public void searchForRepo(String repo) {
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repo);
        $(".header-search-input").submit();
    }

    @Step("Кликнуть по репозиторию с названием {0}")
    public void clickOnLink(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Кликнуть на вкладку Issues")
    public void clickOnIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Проверить, что в списке Issues отображается Issue с номером {0}")
    public void checkThatIssueWithNumberExistsOnPage(int issueNumber) {
        $(withText("#" + issueNumber)).should(Condition.exist);
    }
}
