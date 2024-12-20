package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        // List to store all retrieved categories
        ArrayList<Category> allcats = new ArrayList<>();
        // Try with resources to establish database connection as well as closes connection automatically
        try (Connection connection = getConnection()) {
            // SQL Query to get all categories
            PreparedStatement statement = connection.prepareStatement("""
                    Select * from categories
                    """);
            ResultSet resultSet = statement.executeQuery();
            // Iterate through the result set and map each row to a Category object
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                allcats.add(category); // Add the category to the list
            }
        } catch (SQLException e) {
            // Throw a runtime exception in case of SQL error
            throw new RuntimeException(e);
        }
        // Return the list of categories
        return allcats;
    }


    @Override
    public Category getById(int categoryId) {
        // Initialize the category object
        Category category = null;
        // Try with resources to establish database connection as well as closes connection automatically
        try (Connection connection = getConnection()) {
            // SQL query to fetch a category by its ID
            PreparedStatement statement = connection.prepareStatement("""
                    Select * From categories Where category_id = ?;
                     """);
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                // Map the result row to a Category object if found
                category = mapRow(rs);

            } else {
                System.out.println(" Cannot be found");


            }
        } catch (SQLException e) {
            // Throw a runtime exception in case of SQL error
            throw new RuntimeException(e);
        }
        // Return the found category (or null if not found)
        return category;

    }

    @Override
    public Category create(Category category) {
        try (Connection connection = getConnection()) {
            // SQL query to insert a new category into the database
            PreparedStatement statement = connection.prepareStatement("""
                    Insert into categories Values(null, ?, ?);
                    """, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                // Retrieve the generated category ID and return the new Category object
                int id = keys.getInt(1);
                return new Category(id, category.getName(), category.getDescription());
            }
        } catch (SQLException e) {
            // Throw a runtime exception in case of SQL error
            throw new RuntimeException(e);
        }
        // create a new category
        return null;
    }

    @Override
    public void update(int categoryId, Category category) {
        try(Connection connection = getConnection()){
            // SQL query to update an existing category
            PreparedStatement statement = connection.prepareStatement("""
                    Update categories Set name = ?, description = ?
                    Where category_id = ?;
                    """);
            statement.setString(1,category.getName()); // Get category name
            statement.setString(2, category.getDescription()); // Get category description
            statement.setInt(3,categoryId); // Get category id
            int rows = statement.executeUpdate();
            if (rows == 0){
                // Throw an exception if no rows were updated (invalid ID)
                throw new IllegalArgumentException("Category ID cannot be found ");
            }

        } catch (SQLException e) {
            // Throw a runtime exception in case of SQL error
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int categoryId) {
        try (Connection connection = getConnection()) {
            // SQL query to delete a category by its ID
            PreparedStatement statement = connection.prepareStatement("""
                    Delete from categories Where category_id = ?;
                    
                    """);
            // Set the category ID to delete
            statement.setInt(1, categoryId);
            statement.execute();
        } catch (SQLException e) {
            // Throw a runtime exception in case of SQL error
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet resultSet) throws SQLException {
        {
            // Map a single row from the ResultSet to a Category object
            int categoryId = resultSet.getInt("category_id"); // Get the category ID
            String name = resultSet.getString("name"); // Get the category name
            String description = resultSet.getString("description"); // Get the category description

           // Create and return a new category object
            Category category = new Category() {{
                setCategoryId(categoryId);
                setName(name);
                setDescription(description);
            }};

            return category;
        }
    }
}
