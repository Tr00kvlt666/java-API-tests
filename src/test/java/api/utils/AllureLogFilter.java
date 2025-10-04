package api.utils;

import io.qameta.allure.Allure;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class AllureLogFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // Логируем запрос
        Allure.addAttachment("Запрос", "text/plain",
                String.format("Method: %s\nURL: %s\nHeaders: %s",
                        requestSpec.getMethod(),
                        requestSpec.getURI(),
                        requestSpec.getHeaders()));

        Response response = ctx.next(requestSpec, responseSpec);

        // Логируем ответ
        Allure.addAttachment("Ответ", "text/plain",
                String.format("Status: %s\nBody: %s",
                        response.getStatusCode(),
                        response.getBody().asString()));

        return response;
    }
}