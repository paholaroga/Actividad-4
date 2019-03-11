/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author frida
 */
public class actionGame extends HttpServlet {
    
     static int jugadasRealizadas = 0;
        static int  jugadasEmpatadas = 0;
        static int  jugadasGanadasCliente = 0;
        static int  jugadasGanadasServidor = 0;
        static int  partidasEmpatadas = 0;
        static int  partidasGanadasCliente = 0;
        static int  partidasGanadasServidor = 0;
        int jugadasLimite = 5;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet actionGame</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet actionGame at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        RequestDispatcher requestDispatcher; 
        requestDispatcher = request.getRequestDispatcher("juego.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        String opcionCliente = "";
        String opcionServidor = "";
        String ganadorJugada = "";
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        
        Random aleatorio = new Random(System.currentTimeMillis());
        int intAletorio = aleatorio.nextInt(2) + 1;
        
       // jugador cliente = new jugador();
        //jugador servidor = new jugador();
        
        if(jugadasRealizadas == jugadasLimite){
            jugadasEmpatadas = 0;
            jugadasRealizadas = 0;
            jugadasGanadasServidor = 0;
            jugadasGanadasCliente = 0;
            
            if(jugadasGanadasCliente > jugadasGanadasServidor){
                partidasGanadasCliente++;
            }else{
                partidasGanadasServidor++;
            }
    }
        if(opcion == intAletorio){
            jugadasEmpatadas++;
            jugadasRealizadas++;
            ganadorJugada = "Empate";
        }else{
            if(opcion == 1 && intAletorio == 2){
                jugadasGanadasServidor++;
                ganadorJugada = "Servidor";
            }else if (opcion == 1 && intAletorio == 3){
                jugadasGanadasCliente++;
                ganadorJugada = "Cliente";
            }else if (opcion == 2 && intAletorio == 1){
                jugadasGanadasCliente++;
                ganadorJugada = "Cliente";
            }else if (opcion == 2 && intAletorio == 3){
                jugadasGanadasServidor++;
                ganadorJugada = "Servidor";
            }else if (opcion == 3 && intAletorio == 1){
                jugadasGanadasServidor++;
                ganadorJugada = "Servidor";
            }else if (opcion == 3 && intAletorio == 2){
                jugadasGanadasCliente++;
                ganadorJugada = "Cliente";
            }
            jugadasRealizadas++;
        }
        
        //cliente.aumentaPuntajeGanado();
        opcionCliente = getOpcion(opcion);
        opcionServidor = getOpcion(intAletorio);
        request.setAttribute("opcionCliente", opcionCliente);
        request.setAttribute("opcionServidor", opcionServidor);
        request.setAttribute("jugadasGanadasCliente", jugadasGanadasCliente);
        request.setAttribute("jugadasGanadasServidor", jugadasGanadasServidor);
        request.setAttribute("partidasGanadasCliente", partidasGanadasCliente);
        request.setAttribute("partidasGanadasServidor", partidasGanadasServidor);
        request.setAttribute("partidasEmpatadas", partidasEmpatadas);
        request.setAttribute("jugadasEmpatadas", jugadasEmpatadas);
        request.setAttribute("ganadorJugada", ganadorJugada);
        
        
        
        RequestDispatcher requestDispatcher; 
        requestDispatcher = request.getRequestDispatcher("juego.jsp");
        requestDispatcher.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    private String getOpcion(int opcion){
        String text = "";
        switch(opcion){
            case 1:
                text = "Piedra";
                break;
            case 2:
                text = "Papel";
                break;
            case 3:
                text = "Tijera";
                break;
        }
        return text;
    }

}
