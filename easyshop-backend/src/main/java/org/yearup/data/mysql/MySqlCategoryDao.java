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
        ArrayList<Category> allcats = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    Select * from categories
                    """);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                allcats.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allcats;
    }


    @Override
    public Category getById(int categoryId) {
        Category category = null;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    Select * From categories Where category_id = ?;
                     """);
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                category = mapRow(rs);

            } else {
                System.out.println(" Cannot be found");


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return category;

    }

    @Override
    public Category create(Category category) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    Insert into categories Values(null, ?, ?);
                    """, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                return new Category(id, category.getName(), category.getDescription());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // create a new category
        return null;
    }

    @Override
    public void update(int categoryId, Category category) {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("""
                    Update categories Set name = ?, description = ?
                    Where category_id = ?;
                    """);
            statement.setString(1,category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3,categoryId);
            int rows = statement.executeUpdate();
            if (rows == 0){
                throw new IllegalArgumentException("Category ID cannot be found ");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int categoryId) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("""
                    Delete from categories Where category_id = ?;
                    
                    """);
            statement.setInt(1, categoryId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet resultSet) throws SQLException {
        {
            int categoryId = resultSet.getInt("category_id");
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");

            Category category = new Category() {{
                setCategoryId(categoryId);
                setName(name);
                setDescription(description);
            }};

            return category;
        }
    }
}
