# Домашнее задание к занятию «2.4. BDD»

![Java CI with Maven](https://github.com/andrewquinceee/aqa-page-object/actions/workflows/main.yml/badge.svg)

## Описание
Реализация паттерна Page Object для тестирования функции перевода средств между картами в приложении `app-ibank` с использованием Selenide и JUnit 5.

## ⚠️ Известная особенность (Known Issue)
В среде GitHub Actions тест `MoneyTransferTest` может падать с ошибкой `Element not found {.list__item}` из-за нестабильной отрисовки DOM-дерева приложением `app-ibank` в headless-режиме на Ubuntu. 

Данное поведение задокументировано и описано в [Issue #1](../../issues/1). Локально на машине разработчика тесты проходят стабильно и полностью покрывают требуемую логику проверки динамического баланса (без хардкода суммы, как того требует задание).
