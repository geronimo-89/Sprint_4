package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;
    private JavascriptExecutor js;

    //Кнопки Яндекс, Самокат
    private By homePage = By.className("Home_HomePage__ZXKIX");
    private By homePageButton = By.className("Header_LogoScooter__3lsAR"); //Кнопка Скутера

    //Кнопки заказа
    private By orderButtonUpper = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']"); //кнопка Заказать вверху страницы
    private By orderButtonLower = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']"); //кнопка Заказать внизу страницы

    //Раздел Вопросы о важном
    private By question0 = By.id("accordion__heading-0"); // Вопрос 1: Сколько это стоит? И как оплатить?
    private By answer0 = By.id("accordion__panel-0"); //ответ на вопрос 1
    private By question1 = By.id("accordion__heading-1"); // Вопрос 2: Хочу сразу несколько самокатов! Так можно?
    private By answer1 = By.id("accordion__panel-1"); //ответ на вопрос 2
    private By question2 = By.id("accordion__heading-2"); // Вопрос 3: Как рассчитывается время аренды?
    private By answer2 = By.id("accordion__panel-2"); //ответ на вопрос 3
    private By question3 = By.id("accordion__heading-3"); // Вопрос 4: Можно ли заказать самокат прямо на сегодня?
    private By answer3 = By.id("accordion__panel-3"); //ответ на вопрос 4
    private By question4 = By.id("accordion__heading-4"); // Вопрос 5: Можно ли продлить заказ или вернуть самокат раньше?
    private By answer4 = By.id("accordion__panel-4"); //ответ на вопрос 5
    private By question5 = By.id("accordion__heading-5"); // Вопрос 6: Вы привозите зарядку вместе с самокатом?
    private By answer5 = By.id("accordion__panel-5"); //ответ на вопрос 6
    private By question6 = By.id("accordion__heading-6"); // Вопрос 7: Можно ли отменить заказ?
    private By answer6 = By.id("accordion__panel-6"); //ответ на вопрос 7
    private By question7 = By.id("accordion__heading-7"); // Вопрос 8: Я живу за МКАДом, привезёте?
    private By answer7 = By.id("accordion__panel-7"); //ответ на вопрос 8
    private By[] questions = {question0, question1, question2, question3, question4, question5, question6, question7};
    private By[] answers = {answer0, answer1, answer2, answer3, answer4, answer5, answer6, answer7};

    //Cookies
    private By acceptCookiesButton = By.className("App_CookieButton__3cvqF"); //"да все привыкли" = кнопка принятия Кукиз

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //Найти ответ на вопрос с нужным номером и промотать до него
    public void scrollToQuestion(int number) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(questions[number]));

    }

    //Принять куки
    public void acceptCookies() {
        driver.findElement(acceptCookiesButton).click();
    }

    //Получить ответ на вопрос с выбранным номером со страницы
    public String getAnswer(int number) throws Exception {
        if (number < 0 || number > (answers.length - 1)) {
            throw new Exception("Question number must be between 0 and 7");
        }
        driver.findElement(questions[number]).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(answers[number]));
        String answer = driver.findElement(answers[number]).getText();
        return answer;
    }

    //Кликаем кнопку Заказать вверху страницы
    public void orderMethod1() {
        driver.findElement(orderButtonUpper).click();
    }

    //Кликаем кнопку Заказать внизу страницы
    public void orderMethod2() {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonLower));
        driver.findElement(orderButtonLower).click();
    }

    //Переход к главной странице через логотип Скутера
    public void goToHomePage() {
        driver.findElement(homePageButton).click();
    }

    //Ожидание загрузки главной страницы
    public void waitForHomePage() {
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(homePage));
    }

    //Показать текущий URL страницы
    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }


}
