async function loadAlerts() {
  const token = localStorage.getItem("token");

  const res = await fetch(`${API_BASE_URL}/alerts`, {
    headers: { "Authorization": `Bearer ${token}` }
  });

  if (!res.ok) return;

  const alerts = await res.json();
  const tbody = document.getElementById("alertTable");
  tbody.innerHTML = "";

  alerts.forEach(a => {
    tbody.innerHTML += `
      <tr>
        <td>₹ ${a.targetPrice}</td>
        <td>
          ${a.triggered
            ? '<span class="badge bg-success">Triggered</span>'
            : '<span class="badge bg-warning">Pending</span>'}
        </td>
        <td>${a.triggeredAt || "-"}</td>
      </tr>
    `;
  });
}