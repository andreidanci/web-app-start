package my.apps.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/shoppingList")
public class ShoppingListServlet extends HttpServlet {

    private int counter;
    private static final String LIST_ACTION = "list";

    Item[] items={

            new Item("Paine", "150"),
            new Item("Mere", "3"),
            new Item("Mere", "15"),
            new Item("Pasta De Dinti", "2"),
            new Item("Pasta De Icre", "2"),

    };

    int contor = 5;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("my shopping list service called now.");

        String action = request.getParameter("action");
        counter++;

        if (action != null && action.equals(LIST_ACTION)) {
            listAction(request, response);
        } else if (action != null && action.equals("add")){

            addAction(request, response);
        }

        System.out.println("I was used " + counter + " times!");
    }

    private void addAction(HttpServletRequest request, HttpServletResponse response) {

        String produs = request.getParameter("produs");
        String cantitate = request.getParameter("cantitate");

        Item itemuNou = new


    }

    private void listAction(HttpServletRequest request, HttpServletResponse response) {


        String jsonResponse = "[";
        for (int i=0; i< items.length; i++){
            String nume = items[i].getNume();
            String cantitate = items[i].getCantitate();
            String element = "{\"nume\":\"" + nume + "\", \"cantitate\": " + cantitate + "}";
            if (i < items.length - 1)
            jsonResponse += ",";
        };
            jsonResponse += "]";
            returnJsonResponse(response, jsonResponse);
        String jsonResponse = "[\n" +
                "  {\"nume\": \"Paine\", \"cantitate\": 70},\n" +
               "  {\"nume\": \"Suc\", \"cantitate\": 3},\n" +
                "  {\"nume\": \"Mere\", \"cantitate\": 10},\n" +
                "  {\"nume\": \"Pasta de dinti\", \"cantitate\": 2}\n" +
                "] ";
        returnJsonResponse(response, jsonResponse);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init() called. Counter is: " + counter);
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet! Counter is:" + counter);
        super.destroy();
    }

    /**/
    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}