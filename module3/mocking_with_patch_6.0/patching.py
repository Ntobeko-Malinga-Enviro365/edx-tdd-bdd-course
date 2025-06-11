import requests
from unittest import mock


def imdb_info(title):
    """Gets results for a movie by title"""
    print(f"Searching IMDB for {title}")
    results = requests.get(f"https://imdb-api.com/API/SearchTitle/{title}")
    return results.json()


if __name__ == "__main__":
    with mock.patch("__main__.imdb_info", return_value={"status_code": 200}) as imdb:
        print(imdb_info("Bambi"))
