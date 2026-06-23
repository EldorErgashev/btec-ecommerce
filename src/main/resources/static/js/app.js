// Global API configuration
const API_BASE = '/api';

// Sample products - bu backenddan keladi
const sampleProducts = [
    {
        id: 1,
        name: "USB-C Sim",
        category: "simlar",
        description: "Yuqori tezlik, uzun turinuvchi USB-C sim",
        price: 45000,
        stock: 50,
        imageUrl: "https://via.placeholder.com/250?text=USB-C+Cable"
    },
    {
        id: 2,
        name: "Lightning Sim",
        category: "simlar",
        description: "Apple qurilmalari uchun MFi sertifikatlangan",
        price: 55000,
        stock: 30,
        imageUrl: "https://via.placeholder.com/250?text=Lightning+Cable"
    },
    {
        id: 3,
        name: "65W Quvvatlash Bloki",
        category: "quvvatlash",
        description: "Tez quvvatlash texnologiyasi, 65W imkoniyat",
        price: 150000,
        stock: 20,
        imageUrl: "https://via.placeholder.com/250?text=65W+Charger"
    },
    {
        id: 4,
        name: "30W Quvvatlash Bloki",
        category: "quvvatlash",
        description: "O'rtacha quvvatlash, kompakt dizayn",
        price: 85000,
        stock: 40,
        imageUrl: "https://via.placeholder.com/250?text=30W+Charger"
    },
    {
        id: 5,
        name: "Wireless Naushniklar",
        category: "naushniklar",
        description: "ANC texnologiyasi bilan, 30 soat batareyasi",
        price: 450000,
        stock: 15,
        imageUrl: "https://via.placeholder.com/250?text=Wireless+Earbuds"
    },
    {
        id: 6,
        name: "Kabel Naushniklar",
        category: "naushniklar",
        description: "Sof ovoz, ergonom dizayn, og'irlik 180g",
        price: 280000,
        stock: 25,
        imageUrl: "https://via.placeholder.com/250?text=Wired+Headphones"
    }
];

// Initialize sample data on app start
async function initializeSampleData() {
    try {
        const response = await fetch(`${API_BASE}/products`);
        const products = await response.json();
        
        if (products.length === 0) {
            console.log('Bo\'sh baza, namunaviy ma\'lumotlarni qo\'shmoqda...');
            for (let product of sampleProducts) {
                await fetch(`${API_BASE}/products`, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(product)
                });
            }
        }
    } catch (error) {
        console.error('Initialization error:', error);
    }
}

// Fetch all products
async function fetchProducts() {
    try {
        const response = await fetch(`${API_BASE}/products`);
        return await response.json();
    } catch (error) {
        console.error('Mahsulotlarni yuklashda xato:', error);
        return [];
    }
}

// Fetch products by category
async function fetchProductsByCategory(category) {
    try {
        const response = await fetch(`${API_BASE}/products/category/${category}`);
        return await response.json();
    } catch (error) {
        console.error('Kategoriyani yuklashda xato:', error);
        return [];
    }
}

// Search products
async function searchProducts(query) {
    try {
        const response = await fetch(`${API_BASE}/products/search?query=${encodeURIComponent(query)}`);
        return await response.json();
    } catch (error) {
        console.error('Izlashda xato:', error);
        return [];
    }
}

// Get single product
async function getProduct(id) {
    try {
        const response = await fetch(`${API_BASE}/products/${id}`);
        return await response.json();
    } catch (error) {
        console.error('Mahsulotni yuklashda xato:', error);
        return null;
    }
}

// Shopping Cart Functions
class ShoppingCart {
    constructor() {
        this.items = this.loadCart();
    }
    
    loadCart() {
        const saved = localStorage.getItem('cart');
        return saved ? JSON.parse(saved) : [];
    }
    
    saveCart() {
        localStorage.setItem('cart', JSON.stringify(this.items));
    }
    
    addItem(product) {
        const existing = this.items.find(item => item.id === product.id);
        if (existing) {
            existing.quantity += 1;
        } else {
            this.items.push({ ...product, quantity: 1 });
        }
        this.saveCart();
    }
    
    removeItem(productId) {
        this.items = this.items.filter(item => item.id !== productId);
        this.saveCart();
    }
    
    getTotal() {
        return this.items.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    }
    
    clear() {
        this.items = [];
        this.saveCart();
    }
}

const cart = new ShoppingCart();

// Initialize app
document.addEventListener('DOMContentLoaded', function() {
    initializeSampleData();
    console.log('✅ E-Commerce App Yuklandiyu!');
});

// Log app version
console.log('%c BTEC E-Commerce v1.0.0', 'color: #667eea; font-size: 14px; font-weight: bold;');
