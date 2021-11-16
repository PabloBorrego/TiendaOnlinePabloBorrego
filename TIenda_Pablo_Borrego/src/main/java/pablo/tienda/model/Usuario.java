package pablo.tienda.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	
	@Id @GeneratedValue
	private int id;
	private int id_rol;
	private String user;
	private String password;
	private String email;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private String provincia;
	private String localidad;
	private String telefono;
	private String dni;
	
	

	public Usuario(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	
	public Usuario(int id, int id_rol, String user, String password, String email, String apellido1, String apellido2,
			String direccion, String provincia, String localidad, String telefono, String dni) {
		super();
		this.id = id;
		this.id_rol = id_rol;
		this.user = user;
		this.password = password;
		this.email = email;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
		this.provincia = provincia;
		this.localidad = localidad;
		this.telefono = telefono;
		this.dni = dni;
	}


	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getId_rol() {
		return id_rol;
	}



	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getApellido1() {
		return apellido1;
	}



	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}



	public String getApellido2() {
		return apellido2;
	}



	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getProvincia() {
		return provincia;
	}



	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}



	public String getLocalidad() {
		return localidad;
	}



	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getDni() {
		return dni;
	}



	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	

}
