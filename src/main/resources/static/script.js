document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('translateForm');
    form.addEventListener('submit', async (event) => {
        event.preventDefault();
        
        const inputText = document.getElementById('inputText').value;
        const targetLanguage = document.getElementById('targetLanguage').value;

        const response = await fetch('/api/translate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                words: inputText.split(' '),
                targetLanguage: targetLanguage,
            }),
        });

        if (response.ok) {
            const data = await response.json();
            document.getElementById('resultText').innerText = data.translatedText;
        } else {
            alert('Translation failed. Please try again.');
        }
    });
});

