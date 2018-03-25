/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

import java.util.*;
import proyectoia.Nodo.*;
import java.math.*;

/**
 *
 * @author juan
 */
public class BusquedaInformada_Heuristica {

    int target_pos_x;
    int target_pos_y;
    int init_pos_x;
    int init_pos_y;

    void find_cur_pos(int maze[][]) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 2) {
                    init_pos_x = i;
                    init_pos_y = j;
                    return;
                }

            }

        }
    }

    void find_target_pos(int maze[][]) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 5) {
                    target_pos_x = i;
                    target_pos_y = j;
                    return;
                }

            }

        }
    }

    String find_move(int maze[][], int cur_pos_x, int cur_pos_y) {
        String sol = "";
        if (cur_pos_x != 0) {
            if (maze[cur_pos_x - 1][cur_pos_y] != 1) {
                sol += "8";
            }
        }
        if (cur_pos_x != maze.length - 1) {
            if (maze[cur_pos_x + 1][cur_pos_y] != 1) {
                sol += "2";
            }
        }
        if (cur_pos_y != 0) {
            if (maze[cur_pos_x][cur_pos_y - 1] != 1) {
                sol += "4";
            }
        }
        if (cur_pos_y != maze[0].length - 1) {
            if (maze[cur_pos_x][cur_pos_y + 1] != 1) {
                sol += "6";
            }
        }
        return sol;
    }

    void traceRoute(Vector<Nodo> camino) {
        int size = camino.size();
        int id = camino.get(size - 1).getId();
        String route = "";
        for (int i = size - 1; i >= 0; i--) {
            if (camino.get(i).getId() == id && camino.get(i).getId() != 0) {
                id = camino.get(i).getParent_id();
                route = camino.get(i).getMov() + "\n" + route;

            }
        }
        System.out.print(route);

    }

    int manhatan_Distance(int pos_x, int pos_y) {
        int respuesta = 0;
        int diferencia_x = pos_x - target_pos_x;
        int diferencia_y = pos_y - target_pos_y;
        respuesta = Math.abs(diferencia_x) + Math.abs(diferencia_y);
        return respuesta;
    }

    Vector organizar(Vector<Nodo> arreglo, Nodo new_nodo, int pos_ini, int pos_fin) {
        Vector<Nodo> ini = new Vector();
        Vector<Nodo> fin = new Vector();
        int pos_busqueda = (int) ((pos_ini + pos_fin) / 2);
        if (pos_ini != pos_fin && arreglo.size() > 1) {
            //System.out.println(arreglo.size());
            if (new_nodo.getProductividad() >= arreglo.get(pos_busqueda - 1).getProductividad()) {
                organizar(arreglo, new_nodo, pos_busqueda, pos_fin);
            } else {
                organizar(arreglo, new_nodo, pos_ini, pos_busqueda);
            }
        } else if (arreglo.size() > 1) {
            for (int i = 0; i <= pos_busqueda; i++) {
                ini.add(arreglo.get(i));
            }
            for (int i = arreglo.size(); i > pos_busqueda; i--) {
                Vector<Nodo> auxiliar = new Vector();
                fin.clear();
                fin.add(arreglo.get(i));
                fin.addAll(auxiliar);
            }
            arreglo.clear();
            arreglo.addAll(ini);
            arreglo.add(new_nodo);
            arreglo.addAll(fin);
            ini.clear();
            fin.clear();
        } else if (arreglo.isEmpty()) {
            arreglo.add(new_nodo);
        } else if(arreglo.get(0).getProductividad()> new_nodo.getProductividad()){
            fin.addAll(arreglo);
            arreglo.clear();
            arreglo.add(new_nodo);
            arreglo.addAll(fin);
            fin.clear();
        }else{
            arreglo.add(new_nodo);
        }

        return arreglo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BusquedaInformada_Heuristica aux = new BusquedaInformada_Heuristica();
        int id = 0;
        Nodo nod = new Nodo(id, id, false);
        Vector<Nodo> arbol = new Vector();
        Vector<Nodo> hojas = new Vector();
        Vector<Nodo> hijos = new Vector();
        hojas.add(nod);
        int maze[][] = {
            {0, 3, 0, 0, 0, 0, 1, 1, 0, 1},
            {4, 1, 1, 1, 1, 0, 1, 0, 0, 0},
            {4, 0, 0, 1, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 4, 0, 1, 0},
            {4, 4, 4, 3, 0, 1, 1, 0, 0, 5},
            {2, 1, 0, 0, 1, 1, 1, 4, 1, 0},
            {0, 1, 1, 0, 0, 0, 1, 4, 1, 0},
            {0, 0, 1, 1, 1, 0, 4, 4, 1, 0},
            {1, 0, 0, 0, 1, 0, 1, 4, 0, 0},
            {1, 1, 1, 0, 0, 0, 1, 0, 1, 1}
        };
        aux.find_cur_pos(maze);
        aux.find_target_pos(maze);
        maze[aux.init_pos_x][aux.init_pos_y] = 0;
        hojas.get(0).setCur_pos_x(aux.init_pos_x);
        hojas.get(0).setCur_pos_y(aux.init_pos_y);
        System.out.println("Initial posicion: (" + aux.init_pos_x + "," + aux.init_pos_y + ")");
        System.out.println("Target posicion: (" + aux.target_pos_x + "," + aux.target_pos_y + ")");
        Boolean flag = true;

        do {

            if (hojas.get(0).getCur_pos_x() == aux.target_pos_x && hojas.get(0).getCur_pos_y() == aux.target_pos_y) {
                flag = false;
                arbol.add(hojas.get(0));
            }
            if (flag) {
                String moves = aux.find_move(maze, hojas.get(0).getCur_pos_x(), hojas.get(0).getCur_pos_y());
                if (moves.contains("8") && hojas.get(0).getMov() != "down") {
                    id++;
                    Nodo new_son = new Nodo(id, hojas.get(0).getId(), false);
                    new_son.setCur_pos_x(hojas.get(0).getCur_pos_x() - 1);
                    new_son.setCur_pos_y(hojas.get(0).getCur_pos_y());
                    new_son.setMov("up");
                    new_son.setProductividad(aux.manhatan_Distance(new_son.getCur_pos_x(), new_son.getCur_pos_y()));
                    hijos.add(new_son);
                }
                if (moves.contains("6") && hojas.get(0).getMov() != "left") {
                    id++;
                    Nodo new_son = new Nodo(id, hojas.get(0).getId(), false);
                    new_son.setCur_pos_x(hojas.get(0).getCur_pos_x());
                    new_son.setCur_pos_y(hojas.get(0).getCur_pos_y() + 1);
                    new_son.setMov("rigth");
                    new_son.setProductividad(aux.manhatan_Distance(new_son.getCur_pos_x(), new_son.getCur_pos_y()));
                    hijos.add(new_son);
                }
                if (moves.contains("2") && hojas.get(0).getMov() != "up") {
                    id++;
                    Nodo new_son = new Nodo(id, hojas.get(0).getId(), false);
                    new_son.setCur_pos_x(hojas.get(0).getCur_pos_x() + 1);
                    new_son.setCur_pos_y(hojas.get(0).getCur_pos_y());
                    new_son.setMov("down");
                    new_son.setProductividad(aux.manhatan_Distance(new_son.getCur_pos_x(), new_son.getCur_pos_y()));
                    hijos.add(new_son);
                }
                if (moves.contains("4") && hojas.get(0).getMov() != "rigth") {
                    id++;
                    Nodo new_son = new Nodo(id, hojas.get(0).getId(), false);
                    new_son.setCur_pos_x(hojas.get(0).getCur_pos_x());
                    new_son.setCur_pos_y(hojas.get(0).getCur_pos_y() - 1);
                    new_son.setMov("left");
                    new_son.setProductividad(aux.manhatan_Distance(new_son.getCur_pos_x(), new_son.getCur_pos_y()));
                    hijos.add(new_son);
                }

                arbol.add(hojas.get(0));
                hojas.remove(0);
                for (int i = 0; i < hijos.size(); i++) {
                    hojas = aux.organizar(hojas, hijos.get(i), 0, hojas.size());
                }
                hijos.clear();

            }
        } while (flag);
        aux.traceRoute(arbol);
        /*
        String moves = aux.find_move(maze, nod.getCur_pos_x(), nod.getCur_pos_y());
        moves = moves.replaceAll("8", "up \n");
        moves = moves.replaceAll("4", "left \n");
        moves = moves.replaceAll("6", "rigth \n");
        moves = moves.replaceAll("2", "dawn \n");
        System.out.println("Mario can move:\n" + moves);
         */
    }

}
