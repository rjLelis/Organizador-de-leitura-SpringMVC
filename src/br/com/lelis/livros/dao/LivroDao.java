package br.com.lelis.livros.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.lelis.livros.modelo.Livro;

@Repository
public class LivroDao {

	Connection connection;

	@Autowired
	public LivroDao(DataSource dataSource) {
		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adiciona(Livro livro) {

		String sql = "INSERT INTO livros(titulo) VALUES (?)";

		try {
			PreparedStatement psmt = this.connection.prepareStatement(sql);
			psmt.setString(1, livro.getTitulo());
			psmt.execute();
			psmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Livro> lista() {
		try {
			List<Livro> livros = new ArrayList<Livro>();
			String sql = "SELECT * FROM livros";
			PreparedStatement psmt = this.connection.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Livro livro = new Livro();
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setLido(rs.getBoolean("lido"));
				if (rs.getDate("dataFinalizacao") != null) {
					Calendar dataFinalizacao = Calendar.getInstance();
					dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));
					livro.setDataFinalizacao(dataFinalizacao);
				}
				livros.add(livro);
			}
			rs.close();
			psmt.close();
			return livros;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void altera(Livro livro) {
		String sql = "UPDATE livros SET titulo=?, lido=?, dataFinalizacao=? WHERE id=?";
		try {
			PreparedStatement psmt = this.connection.prepareStatement(sql);
			psmt.setString(1, livro.getTitulo());
			psmt.setBoolean(2, livro.isLido());
			if(livro.isLido()) {
				if(livro.getDataFinalizacao() != null) {
					psmt.setDate(3, new Date(livro.getDataFinalizacao().getTimeInMillis()));
				} else {
					psmt.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
				}
			} else {
				psmt.setDate(3, null);
			}
			psmt.setLong(4, livro.getId());
			
			psmt.execute();
			psmt.close();
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(long id) {
		String sql = "DELETE FROM livros WHERE id=?";
		try {
			PreparedStatement psmt = this.connection.prepareStatement(sql);
			psmt.setLong(1, id);
			psmt.execute();
			psmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Livro buscaPorId(long id) {
		Livro livro = null;
		String sql = "SELECT * FROM livros WHERE id=?";
		
		try {
			PreparedStatement psmt = this.connection.prepareStatement(sql);
			psmt.setLong(1, id);
			ResultSet rs = psmt.executeQuery();
			if(rs.next()) {
				livro = new Livro();
				livro.setId(rs.getLong("id"));
				livro.setTitulo(rs.getString("titulo"));
				livro.setLido(rs.getBoolean("lido"));
				if(rs.getDate("dataFinalizacao") != null) {
					Calendar dataFinalizacao = Calendar.getInstance();
					
					dataFinalizacao.setTime(rs.getDate("dataFinalizacao"));
					livro.setDataFinalizacao(dataFinalizacao);
				}
			}
			psmt.close();
			rs.close();
			return livro;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
				
		
	}
	
	public void finaliza(long id) {
		String sql = "UPDATE livros SET lido=?, dataFinalizacao=? WHERE id=?";
		
		try {
			PreparedStatement psmt = this.connection.prepareStatement(sql);
			psmt.setBoolean(1, true);
			psmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			psmt.setLong(3, id);
			
			psmt.execute();
			psmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
