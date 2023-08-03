package com.leventsurer.denemelerim.util.enums

enum class RegisterErrors(val error: String) {
    USERNAME_ERROR("Kullanıcı adı boş bırakılamaz."),
    EMAIL_ERROR("Email boş bırakılamaz."),
    PASSWORD_ERROR("Şifre alanları boş bırakılamaz."),
    SAME_PASSWORD_ERROR("Şifreler uyuşmuyor")
}