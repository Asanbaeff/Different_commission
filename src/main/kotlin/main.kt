fun main() {
    //println(calculateTransfer("Мир", 0.0, 100_000.0)) // Мир
    println(calculateTransfer("Mastercard", 0.0, 75_000.0)) // Mastercard
    println(calculateTransfer("Mastercard", 0.0, 150_000.0)) // Mastercard
    //println(calculateTransfer("Visa", 0.0, 100_000.0)) // Visa
}

fun calculateTransfer(
    cardType: String = "Мир", //тип карты
    previousTransfers: Double = 0.0, //сумма предыдущих переводов в этом месяце
    transferAmount: Double   //сумма совершаемого перевода
): String {
    val dailyLimit = 150_000.0 // Лимиты
    val monthlyLimit = 600_000.0
    val mastercardLimit = 75_000.0

    if (transferAmount > dailyLimit) { // Проверка лимитов
        return "Перевод превышает суточный лимит в 150 000 руб."
    }
    if (previousTransfers + transferAmount > monthlyLimit) {
        return "Перевод превышает месячный лимит в 600 000 руб."
    }

    val commission: Double = when (cardType) { // Расчет комиссии
        "Mastercard" -> {
            if (previousTransfers + transferAmount <= mastercardLimit) {
                0.0
            } else {
                val excess = (transferAmount - mastercardLimit) * 0.006 + 20
                excess
            }
        }

        "Visa" -> {
            val calculatedCommission = transferAmount * 0.0075
            if (calculatedCommission < 35) 35.0 else calculatedCommission
        }

        "Мир" -> 0.0
        else -> return "Неизвестный тип карты"
    }
    val totalReceived = transferAmount - commission
    return "Сумма перевода: $transferAmount руб., Комиссия: $commission руб., Получит: $totalReceived руб. (карта $cardType)"
}

