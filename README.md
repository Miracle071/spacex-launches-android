# SpaceX Launches – Android App (Kotlin + Compose)

Android-приложение для просмотра запусков SpaceX по году.  
Реализовано в рамках курсового проекта: 2 экрана, HTTP-запрос к открытому API, сохранение результатов в БД и вывод списка.

## Функциональность

1. **Экран ввода года**
    - Поле для ввода года (например, `2020`).
    - Кнопка `Load and save launches`.
    - По нажатию:
        - выполняется запрос к [SpaceX API](https://github.com/r-spacex/SpaceX-API) (`GET /v5/launches`);
        - запуски фильтруются по году (по полю `date_utc`);
        - данные сохраняются в локальную базу данных Room.

2. **Экран списка запусков**
    - Заголовок “Saved SpaceX launches”.
    - Список запусков, сохранённых в БД:
        - название запуска;
        - дата (UTC);
        - флаг успеха (`success`);
        - короткое описание миссии (`details`).
    - Кнопка “назад” в `TopAppBar` для возврата к экрану ввода.

## Технологии

- **Язык:** Kotlin
- **UI:** Jetpack Compose, Material3
- **HTTP:** Retrofit + Gson converter
- **База данных:** Room
- **DI:** Hilt
- **Архитектура:** MVVM + Repository

## Структура проекта (основное)

- `SpaceXApp` – класс `Application` с `@HiltAndroidApp`.
- `MainActivity` – `@AndroidEntryPoint`, хостит навигацию Compose.

**Data layer**

- `data.remote.dto.LaunchDto` – модель ответа SpaceX API.
- `data.remote.dto.SpaceXApi` – интерфейс Retrofit (`getAllLaunches()`, `getLatestLaunch()`).
- `data.local.LaunchEntity` – сущность Room (`tableName = "launches"`).
- `data.local.LaunchDao` – DAO (`getAllLaunches`, `insertLaunches`, `clearAll`).
- `data.local.AppDatabase` – RoomDatabase.
- `data.LaunchMappers.kt` – маппер `LaunchDto.toEntity()`.
- `data.LaunchRepository` – работа с API + БД:
    - `fetchLaunchesForYear(year)` – загрузка из сети, фильтрация и сохранение;
    - `getSavedLaunches()` – чтение из БД.

**DI**

- `di.NetworkModule` – `Retrofit` + `SpaceXApi`, `baseUrl = https://api.spacexdata.com/`.
- `di.DatabaseModule` – `AppDatabase` и `LaunchDao`.

**Presentation**

- `presentation.LaunchInputViewModel`
- `presentation.SavedLaunchesViewModel`
- `presentation.LaunchInputScreen`
- `presentation.SavedLaunchesScreen`

## Как запустить

1. Клонировать репозиторий:

   ```bash
   git clone https://github.com/Miracle071/spacex-launches-android.git
   cd spacex-launches-android
