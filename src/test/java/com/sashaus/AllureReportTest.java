package com.sashaus;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureReportTest {

    private static final String REPOSITORY = "sashaus9/hw_11_allure";
    private static final int ISSUE = 1;

    @Test
    @DisplayName("Чистый Selenide (с Listener)")
    void checkIssueNameWithSelenideAndListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(REPOSITORY);
        $(".header-search-input").submit();

        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText("#" + ISSUE)).should(Condition.exist);
    }

    @Test
    @DisplayName("Лямбда шаги через step (name, () -> {})")
    void checkIssueNameWithLambdaStepTest() {
        step("Открыть главную страницу github", () -> {
            open("https://github.com");
        });
        step("Найти репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Кликнуть по репозиторию с названием " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Кликнуть на вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверить, что в списке Issues отображается Issue с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Шаги с аннотацией @Step")
    void checkIssueNameWithAllureStepTest() {
        Steps steps = new Steps();

        steps.openMainPage();
        steps.searchForRepo(REPOSITORY);
        steps.clickOnLink(REPOSITORY);
        steps.clickOnIssuesTab();
        steps.checkThatIssueWithNumberExistsOnPage(ISSUE);
    }
}
