package sample;

import java.sql.*;

public class DbConnection {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/javabasesoop";
    static final String USER = "postgres";
    static final String PASS ="879639";
    Connection connection = null;

    public DbConnection() throws SQLException {
       try{
           connection= DriverManager.getConnection(DB_URL,USER,PASS);
       }catch (SQLException e)
       {
           e.printStackTrace();
       }
       getPlants();
    }
    public Plant getPlant(long id)throws SQLException{
        Statement st=connection.createStatement();
        ResultSet resultSet=st.executeQuery("SELECT * FROM plantshop WHERE id='"+id+"';");//execute Query - выполнить запрос к базе
        Plant receivedPlant=new Plant();
        while(resultSet.next()) {
            receivedPlant.setId(resultSet.getLong("id"));
            receivedPlant.setName(resultSet.getString("plantname"));
            receivedPlant.setColour(resultSet.getString("colour"));
            receivedPlant.setPrice(resultSet.getBigDecimal("price"));
            receivedPlant.setSort(resultSet.getString("sort"));
        }
                st.close();
                resultSet.close();
       return receivedPlant;
    }
    public void getPlants()throws SQLException{
        Plant.clearPlantArray();
        Statement st=connection.createStatement();//представить что есть ты и база - коннекш это тунель между вами,а стейтмент это паровозик,который ходит туда и обратно!
        ResultSet resultSet=st.executeQuery("SELECT * FROM plantshop;");//итоговая коллекция полученная с базы
        while(resultSet.next()){
            Plant.addPlant(
                    resultSet.getLong("id"),
                    resultSet.getString("plantname"),
                    resultSet.getString("colour"),
                    resultSet.getBigDecimal("price"),
                    resultSet.getString("sort")
            );
        }
        resultSet.close();
        st.close();
    }
    public void addPlant(Plant plant)throws SQLException{//поменять.
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO plantshop(plantname,colour,price,sort)VALUES('"+plant.getName()+"','"+plant.getColour()+"','"+plant.getPrice()+"','"+plant.getSort()+"');");
            st.close();
        }catch(SQLException e)
        {
            System.out.println("Произошла ошибка при добавлении записи.");
        }
    }
    public void deletePlant(long id)throws SQLException{
        try{
            Statement st=connection.createStatement();
            st.executeUpdate("DELETE FROM plantshop WHERE id="+id+";");
            st.close();
        }catch (SQLException e)
        {
            System.out.println("Невозможно удалить запись.");
        }
    }
    public void editPlant(Plant plant)throws SQLException{
        try{
            Statement st=connection.createStatement();
            st.executeUpdate("UPDATE plantshop SET plantname='"+plant.getName()+"',colour='"+ plant.getColour()+"',price='"+plant.getPrice()+"',sort='"+plant.getSort()+"' WHERE id="+plant.getId()+";");
            st.close();
        }catch (SQLException e)
        {
            System.out.println("Невозможно изменить запись.");
        }
    }


}
