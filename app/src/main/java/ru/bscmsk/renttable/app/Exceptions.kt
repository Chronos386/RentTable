package ru.bscmsk.renttable.app

import java.lang.Exception

class UnauthorizedException(message: String = "User is not authorized") : Exception(message)