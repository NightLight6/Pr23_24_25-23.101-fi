package com.example.pr23_23101_fi

data class AnalysisItem(
    val id: Int,
    val name: String,
    val price: String,
    val category: String,
    val imageResId: Int
)

val mockAnalyses = listOf(
    AnalysisItem(1, "Общий анализ крови", "500 ₽", "popular", R.drawable.ic_analyses),
    AnalysisItem(2, "Биохимия", "1200 ₽", "popular", R.drawable.ic_analyses),
    AnalysisItem(3, "ПЦР на COVID-19", "900 ₽", "covid", R.drawable.ic_analyses),
    AnalysisItem(4, "Антитела IgG", "700 ₽", "covid", R.drawable.ic_analyses),
    AnalysisItem(5, "Комплекс 'Женское здоровье'", "3500 ₽", "complex", R.drawable.ic_monitoring),
    AnalysisItem(6, "Комплекс 'Щитовидная железа'", "2800 ₽", "complex", R.drawable.ic_monitoring)
)

val newsItems = listOf(
    "Скидка 20% на ПЦР до 31 мая",
    "Новый метод экспресс-анализов",
    "Подарок при заказе от 5000₽"
)