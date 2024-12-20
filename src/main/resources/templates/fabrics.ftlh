<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новинки - Ткани</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Общие стили */
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }

        .navbar {
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        .navbar-brand {
            font-weight: bold;
        }

        .nav-link {
            transition: color 0.3s ease;
        }

        .nav-link:hover {
            color: #0056b3;
        }

        .card {
            transition: transform 0.3s ease, box-shadow 0.3s ease;
            border: none;
            border-radius: 10px;
        }

        .card:hover {
            transform: translateY(-10px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
        }

        .card-img-top {
            height: 200px;
            object-fit: cover;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }

        .card-body {
            padding: 1.5rem;
        }

        .card-title {
            font-size: 1.2rem;
            font-weight: bold;
            color: #333;
        }

        .card-text {
            font-size: 1rem;
            color: #555;
        }

        .category-item {
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .category-item:hover, .category-item.active {
            background-color: #e9ecef;
            font-weight: bold;
        }

        .navbar-nav {
            font-weight: 500;
        }

        .list-group-item {
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .list-group-item:hover {
            background-color: #f1f1f1;
        }

        .highlight-category {
            background-color: #007bff;
            color: white !important;
        }

        /* Адаптивность */
        @media (max-width: 768px) {
            .card-img-top {
                height: 150px;
            }
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container">
        <a class="navbar-brand" href="#">Ткани</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item"><a class="nav-link" href="#">Ткани</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Трикотаж</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Новинки</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Фурнитура</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Скидки</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Контакты</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-3">
            <h5 class="mb-4">Категории</h5>
            <ul class="list-group" id="list-category">
            </ul>
        </div>

        <div class="col-md-9">
            <h2 class="mb-4">Новинки</h2>
            <div class="row row-cols-1 row-cols-md-3 g-4" id="fabrics">
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script>
    async function fetchProducts(categoryId = '') {
        try {
            const url = categoryId
                ? `http://localhost:8080/api/fabrics?categoryId=` + categoryId
                : 'http://localhost:8080/api/fabrics';
            const response = await fetch(url);

            if (!response.ok) {
                throw new Error('Ошибка при загрузке данных');
            }

            const data = await response.json();
            displayProducts(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    function displayProducts(products) {
        const container = document.getElementById('fabrics');
        container.innerHTML = '';

        products.forEach(fabric => {
            const productCard = `
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <img src="/api/files/` + fabric.imageUrl + `" class="card-img-top" alt="">
                        <div class="card-body text-center flex-grow-1">
                            <h5 class="card-title">` + fabric.name + `</h5>
                        </div>
                        <p class="card-text align-self-center mb-2"><strong>` + fabric.price + ` Сом</strong></p>
                        <div class="card-footer text-center">
                            <button class="btn btn-primary w-100">Купить</button>
                        </div>
                    </div>
                </div>
            `;
            container.innerHTML += productCard;
        });
    }

    document.addEventListener('DOMContentLoaded', fetchProducts);

    async function fetchCategory() {
        try {
            const response = await fetch('http://localhost:8080/api/categories');
            if (!response.ok) {
                throw new Error('Ошибка при загрузке данных');
            }
            const data = await response.json();
            displayCategory(data);
        } catch (error) {
            console.error('Ошибка:', error);
        }
    }

    function displayCategory(categories) {
        const container = document.getElementById('list-category');
        container.innerHTML = '';

        categories.forEach(category => {
            const categoryItem = document.createElement('li');
            categoryItem.classList.add('list-group-item', 'category-item');
            categoryItem.textContent = category.name;
            categoryItem.style.cursor = 'pointer';
            categoryItem.dataset.categoryId = category.id;

            categoryItem.addEventListener('click', () => {
                const categoryId = categoryItem.dataset.categoryId;
                fetchProducts(categoryId);
                highlightCategory(categoryItem);
            });

            container.appendChild(categoryItem);
        });
    }

    function highlightCategory(selectedItem) {
        document.querySelectorAll('#list-category .category-item').forEach(item => {
            item.classList.remove('active');
        });
        selectedItem.classList.add('highlight-category');
    }

    document.addEventListener('DOMContentLoaded', fetchCategory);
</script>
</body>
</html>