/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataAccessLayer;
import Model.ItemDTO;
import javax.servlet.http.HttpSession;

import java.util.List;
/**
 * @author : Mohammad Dellawari
 */

public interface ItemDAO {
    void addItem(ItemDTO item, HttpSession session);
    void selectItem();
    void deleteItem(int itemId);
    

    List<ItemDTO> getAllAvailableItems();
// ---------------------------------- 

}
