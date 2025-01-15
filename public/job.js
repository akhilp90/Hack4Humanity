// Mobile menu toggle
const mobileMenuButton = document.getElementById('mobile-menu-button');
const mobileMenu = document.getElementById('mobile-menu');

mobileMenuButton.addEventListener('click', () => {
mobileMenu.classList.toggle('hidden');
});

// Close mobile menu when clicking outside
document.addEventListener('click', (e) => {
    if (!mobileMenuButton.contains(e.target) && !mobileMenu.contains(e.target)) {
        mobileMenu.classList.add('hidden');
        }
    });

    document.addEventListener('DOMContentLoaded', async () => {
        const hospitalSearchForm = document.getElementById('hospitalSearchForm');
        const hospitalList = document.getElementById('hospitalList');
    
        hospitalSearchForm.addEventListener('submit', async (event) => {
            event.preventDefault();
            const formData = new FormData(hospitalSearchForm);
            const searchParams = new URLSearchParams(formData).toString();
    
            try {
                const response = await fetch(`/api/hospitals?${searchParams}`);
                const hospitals = await response.json();
    
                hospitalList.innerHTML = '';
    
                hospitals.forEach(hospital => {
                    const hospitalDiv = document.createElement('div');
                    hospitalDiv.classList.add('hospital');
    
                    hospitalDiv.innerHTML = `
                        <h2>${hospital.name}</h2>
                        <p><strong>Address:</strong> ${hospital.address}</p>
                        <p><strong>Contact:</strong> ${hospital.contact}</p>
                        <p><strong>Services:</strong> ${hospital.services.join(', ')}</p>
                    `;
    
                    hospitalList.appendChild(hospitalDiv);
                });
            } catch (error) {
                console.error('Error fetching hospitals:', error);
            }
        });
    });