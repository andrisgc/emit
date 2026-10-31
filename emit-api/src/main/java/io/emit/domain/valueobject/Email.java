package io.emit.domain.valueobject;

import io.emit.domain.exception.ValidacaoDominioException;
import java.util.Objects;
import java.util.regex.Pattern;

public final class Email {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    private final String endereco;

    public Email(String endereco) {
        if (!isValido(endereco)) {
            throw new ValidacaoDominioException("Formato de e-mail inválido: " + endereco);
        }

        this.endereco = endereco.trim().toLowerCase();
    }

    public static boolean isValido(String email) {
        return email != null && !email.isBlank() && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Email email = (Email) o;
        return Objects.equals(endereco, email.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endereco);
    }

    @Override
    public String toString() {
        return endereco;
    }
}
