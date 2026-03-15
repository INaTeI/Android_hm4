Никифоров Владимир ЦТЭ Б9123-09.03.03 COUNTRY-ALL-MOD_A2_GRID 
Переделанный вариант с mutablestateof

Jetpack Compose Hilt (DI) Retrofit (HTTP клиент) Navigation Compose MVVM архитектура

Используемое API REST Countries API Endpoints Получение списка стран: GET https://restcountries.com/v3.1/all?fields=name,cca2,flags,capital,region,population

Получение одной страны: GET https://restcountries.com/v3.1/alpha/{code}

VariantCode отображается на главном экране приложения строкой.

Особенности LazyVerticalGrid Карточки (Card) Обработка состояний Loading / Error / Success Переход на экран деталей по нажатию на карточку через Mutablestateof

В приложении Главный экран

VariantCode Сетка стран (Grid)

Карточка страны содержит

Название страны Столицу Флаг

Экран деталей Название страны Столица Регион Население Флаг страны

Скриншоты: список стран детали каждой страны Оформленная анимация загрузки
![photo_5289948663020983476_y](https://github.com/user-attachments/assets/369d2925-3ff2-4960-8f47-e5cf4fa04c98)
![photo_5289948663020983473_y](https://github.com/user-attachments/assets/0f2f36a7-6ead-4540-aad0-ce6e7a2b6eb4)
![photo_5289948663020983475_y](https://github.com/user-attachments/assets/d3a8d85b-b9b0-40b2-bc4b-d4c7553d03cb)
