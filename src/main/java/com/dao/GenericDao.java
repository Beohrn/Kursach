package com.dao;


import java.sql.SQLException;
import java.util.List;

/**
 * Унифицированный интерфейс управления персистентным состоянием объектов
 */
public interface GenericDao<T extends Object> {

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /** Создает новую запись и соответствующий ей объект */
    public T create(T object) throws SQLException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T get(int key) throws SQLException;

    /** Сохраняет состояние объекта в базе данных */
    public void update(T object) throws SQLException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(T object) throws SQLException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() throws SQLException;
}
