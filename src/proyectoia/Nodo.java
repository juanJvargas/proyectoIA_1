/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoia;

/**
 *
 * @author juan
 */
public class Nodo {
    private int  id;
    private int parent_id;
    private boolean parent;
    private int cur_pos_x = 0;
    private int cur_pos_y = 0;
    private String mov;
    
    
    
    public Nodo(int id, int parent_id, boolean parent) {
        this.id = id;
        this.parent_id = parent_id;
        this.parent = parent;
    }

    public int getId() {
        return id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public int getCur_pos_x() {
        return cur_pos_x;
    }

    public void setCur_pos_x(int cur_pos_x) {
        this.cur_pos_x = cur_pos_x;
    }

    public int getCur_pos_y() {
        return cur_pos_y;
    }

    public void setCur_pos_y(int cur_pos_y) {
        this.cur_pos_y = cur_pos_y;
    }

    public String getMov() {
        return mov;
    }

    public void setMov(String mov) {
        this.mov = mov;
    }
    
}
