package trigger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.Collections;

import com.pusher.rest.*;

/* Simple servlet to re-trigger Pusher events from a web browser. */
public class PushyTrigger extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String msg = request.getParameter("msg");

        try {
            out.println("<!DOCTYPE html>");  // HTML 5
            out.println("<html><head>");
            out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" " +
                    "integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" " +
                    "crossorigin=\"anonymous\">");
            out.println("<title>" + "Trigger events" + "</title></head>");
            out.println("<body>");
            out.println("<div class=\"container\">");
            out.println("<h1>" + "Welcome to the control room:" + "</h1>");
            /* Triggering of events */
            out.println("<p>" + "Refresh page to re-trigger some predefined Pusher events." + "</p>");
            out.println("<form action=" + request.getRequestURI() + ">");
            out.println("<input type=\"submit\" value=\"Refresh page and trigger events\" />");
            out.println("</form>");
            out.println("<hr>");
            /* Broadcasting */
            out.println("<h3>Broadcast a short message!</h3>");
            out.println("<br />");
            out.println("<p class=\"text-secondary\">" + "Message in the current broadcasting request:"  + "</p>");
            if (msg != null) {
                out.println("<p class=\"text-secondary\">");
                out.println("Last displayed Message:");
                out.println(" = " + msg);
                out.println("</p>");
                out.println("<br /><br />");
            } else {
                out.println("<p class=\"text-danger\">No message found, please go ahead and enter some text.</p>");
                out.println("<br />");
            }
            out.print("<form method=\"POST\">");
            out.println("Message:");
            out.println("<input type=\"text\" size=\"40\" maxlength=\"280\" name=\"msg\">");
            out.println("(max. 280 chars)<br /><br />");
            out.println("<input type=\"submit\" value=\"Say it loud!\">");
            out.println("</form>");
            out.println("<hr>");

            out.println("</div>");
            out.println("</body></html>");
        } finally {
            out.close();
        }

        /* Connect to Pusher */
        Pusher pusher = new Pusher("470174", "d756bf87f8141ec89336", "c9bf2d26f5ab9113b400");
        pusher.setCluster("eu");
        pusher.setEncrypted(true);

        /* Trigger Window alert() - default example */
        // pusher.trigger("my-channel", "my-event", Collections.singletonMap("message", "hello world"));

        /* Trigger swap text */
        pusher.trigger("my-channel", "ghost_msg", Collections.singletonMap("message", "Boo!! <br />Looks like I can do!"));

        /* Trigger swap picture */
        pusher.trigger("my-channel", "ghost_pic", Collections.singletonMap("message", "img/Ghost_Emoji.png"));

        /* Trigger notification */
        pusher.trigger("notifications", "new_notification", Collections.singletonMap("message", "Notification: Ghost warning issued!!!"));

        /* Trigger message broadcasting */
        pusher.trigger("notifications", "new_broadcast", Collections.singletonMap("message", msg));

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doGet(request, response);
    }

}
