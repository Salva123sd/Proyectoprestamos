private final UsuarioRepositorioPuerto usuarioRepositorioPuerto;

public LoginServicio(UsuarioRepositorioPuerto usuarioRepositorioPuerto) {
    this.usuarioRepositorioPuerto = usuarioRepositorioPuerto;
}

public Usuario login(String email, String password) {
    Usuario usuario = usuarioRepositorioPuerto.buscarPorEmail(email)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    // aquí validas password, etc.
    return usuario;
}
