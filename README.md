# Hangman

## Описание игры

Игра по отгадыванию случайно загаданных слов из словаря.
Вам нужно угадать слово. За один ход вы можете попытаться угадать одну букву. У вас есть 6 попыток, после чего игровой цикл завершится.

## Как начать новую игру

Чтобы начать новую игру, введите **Y**. Чтобы выйти из игры, введите **N**.

## Игровой процесс

1. В начале игры случайным образом выбирается слово из словаря.
2. Количество букв в загаданном слове отображается на экране с помощью маски из символов "*". Например, "******" для слова "собака".
3. Игрок вводит букву кириллицей.
4. Игра проверяет вхождение введённой буквы в загаданное слово.
5. Если введённой буквы в слове нет, то рисуется следующий этап виселицы, и количество оставшихся попыток уменьшается на 1.
6. Если введённая буква есть в слове, то в маске открываются все включения этой буквы.
7. В конце игры отображается результат: выигрыш или проигрыш. Загаданное слово выводится на экран. Игроку предлагается выбор - начать новую игру или выйти из игры.

Вы можете ознакомиться с полными правилами игры на [Википедии](https://ru.wikipedia.org/wiki/%D0%92%D0%B8%D1%81%D0%B5%D0%BB%D0%B8%D1%86%D0%B0_%28%D0%B8%D0%B3%D1%80%D0%B0%29).
