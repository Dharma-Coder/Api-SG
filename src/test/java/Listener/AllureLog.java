package Listener;

import io.qameta.allure.Attachment;
import org.testng.ITestListener;
import org.testng.ITestResult;
import io.restassured.RestAssured;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static io.restassured.filter.log.RequestLoggingFilter.logRequestTo;
import static io.restassured.filter.log.ResponseLoggingFilter.logResponseTo;

public class AllureLog implements ITestListener {
    private final ThreadLocal<ByteArrayOutputStream> requestLog = ThreadLocal.withInitial(ByteArrayOutputStream::new);
    private final ThreadLocal<ByteArrayOutputStream> responseLog = ThreadLocal.withInitial(ByteArrayOutputStream::new);


    @Override
    public void onTestStart(ITestResult result) {
        logFunctionName(result.getMethod().getConstructorOrMethod().getName());
        resetLogs();
        RestAssured.replaceFiltersWith(
                logRequestTo(new PrintStream(requestLog.get())),
                logResponseTo(new PrintStream(responseLog.get()))
        );
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        attachRequestLog(getAndReset(requestLog.get()));
        attachResponseLog(getAndReset(responseLog.get()));
    }


    @Override
    public void onTestFailure(ITestResult result) {
        attachRequestLog(getAndReset(requestLog.get()));
        attachResponseLog(getAndReset(responseLog.get()));
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        attachRequestLog(getAndReset(requestLog.get()));
        attachResponseLog(getAndReset(responseLog.get()));
    }


    @Attachment(value = "Request Log", type = "application/json")
    public String attachRequestLog(String request) {
        return request;
    }


    @Attachment(value = "Response Log", type = "application/json")
    public String attachResponseLog(String response) {
        return response;
    }


    public void logFunctionName(String FName) {
        System.out.println("---------------" + FName + "---------------");
    }


    private void resetLogs() {
        requestLog.get().reset();
        responseLog.get().reset();
    }


    private String getAndReset(ByteArrayOutputStream stream) {
        String content = stream.toString();
        stream.reset(); // Clear the stream for the next test
        return content;
    }
}