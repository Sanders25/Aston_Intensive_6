package com.example.aston_intensive_6.data

import java.io.Serializable

class ContactsRepo : Serializable {

    private var _contacts = getContacts()
    val contactsList: List<Contact> = _contacts

    fun addContact(index: Int, contact: Contact) {
        _contacts.add(index, contact)
    }

    fun deleteContact(index: Int) {
        _contacts.removeAt(index)
    }

    fun deleteContact(contact: Contact) {
        _contacts.remove(contact)
    }

    fun updateContact(index: Int, contact: Contact) {
        _contacts.removeAt(index)
        _contacts.add(index, contact)
    }

    fun restoreContacts(contacts: List<Contact>) {
        _contacts = contacts.toMutableList()
    }

    private fun getContacts() = mutableListOf(
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Андрей Иванов",
            "+7(871)532-52-12"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Михаил Олейников",
            "+7(911)138-47-19"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
        Contact(
            "Вениамин Паравозов",
            "+7(163)665-61-37"
        ),
        Contact(
            "Алексей Дёмин",
            "+7(633)862-78-11"
        ),
    )
}