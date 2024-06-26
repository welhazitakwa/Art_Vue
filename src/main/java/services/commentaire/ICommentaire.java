package services.commentaire;

import java.sql.SQLException;
import java.util.List;

public interface ICommentaire <T>{
    void ajouter (T t) throws SQLException;
    void modifier (T t) throws SQLException ;
    void supprimer (int id) throws SQLException ;
    List<T> getCommentsByOeuvre (int id) throws SQLException ;
}
