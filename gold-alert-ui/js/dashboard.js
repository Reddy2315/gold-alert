// Create alert
async function createAlert() {
  const token = localStorage.getItem("token");
  const price = document.getElementById("price").value;

  const res = await fetch(`${API_BASE_URL}/alerts`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    },
    body: JSON.stringify({ targetPrice: price })
  });

  if (!res.ok) {
    showToast("Failed to create alert", "error");
    return;
  }

  // Clear input
  document.getElementById("price").value = "";

  // Reload alert list immediately
  await loadAlerts();

  showToast("Gold alert created successfully", "success");
}

// Trigger price fetch and alert check
async function triggerPrice() {
  const token = localStorage.getItem("token");

  await fetch(`${API_BASE_URL}/price/fetch`, {
    headers: {
      "Authorization": `Bearer ${token}`
    }
  });

  showToast("Price fetched. If condition met, email will arrive.", "info");
}

// Load alerts and price history on page load
async function loadPriceHistory() {
  const token = localStorage.getItem("token");

  const res = await fetch(`${API_BASE_URL}/price/history`, {
    headers: { "Authorization": `Bearer ${token}` }
  });

  if (!res.ok) return;

  const prices = await res.json();
  const tbody = document.getElementById("priceTable");
  tbody.innerHTML = "";

  prices.forEach(p => {
    tbody.innerHTML += `
      <tr>
        <td>₹ ${p.pricePerGram}</td>
        <td>${p.source}</td>
        <td>${p.fetchedAt}</td>
      </tr>
    `;
  });
}

// Load alerts and price history on page load
document.addEventListener("DOMContentLoaded", () => {
  loadAlerts();
  loadPriceHistory();
});