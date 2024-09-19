# NewsApp
NewsApp is a Kotlin-based Android application that provides users with the latest news from various sources. The app is built using architecture MVVM and the NewsAPI.org service to fetch news articles in real-time. It features like top headlines, technology news, and sports news, with a sleek and user-friendly interface.

## Features
- Top Headlines: Get the latest headlines from around the world.
- Search Functionality: Search for news articles by keywords.
- Article Details: Tap on any article to view the full content with details.

##  Screenshots
- Description: The home screen shows a list of top headlines with category filters.

  ![image](https://github.com/user-attachments/assets/e5467397-977c-4f55-a013-82b28aaa4568)
  

  ![image](https://github.com/user-attachments/assets/423d1f9b-9f0d-4227-91fb-aa646cf1c402)



- Description: The article details screen displays the full content of the selected news article.

  ![image](https://github.com/user-attachments/assets/4fbacda7-6dd0-40f3-a66f-90875bc977d0)


## Installation
1. Clone the repository:
```
git clone https://github.com/username/newsapp.git
```
2. Open the project in Android Studio.
3. Sync the project with Gradle to install the necessary dependencies.
4. Obtain an API key from NewsAPI.org.
5. Add your API key to the local.properties file:
```
news_api_key=your_api_key_here
```
6. Build and run the application on an Android device or emulator.

## Architecture
The application follows the MVVM (Model-View-ViewModel) architecture to ensure a clean and maintainable codebase.
1. ViewModel: Handles the business logic and communicates with the Repository.
2. Repository: Manages data operations, fetching data from the network (using Retrofit) and providing it to the ViewModel.
3. LiveData: Observed by the UI to update the screen when data changes.
4. RecyclerView: Displays lists of news articles, with ViewHolders managing individual items.
