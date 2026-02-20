async function register() {
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  const res = await fetch(`${API_BASE_URL}/auth/register`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  });

  if (res.ok) {
    // clear saved values
    clearFormValues(["register_email", "register_password"]);

    showToast("Registration successful. Please login.", "success");
    window.location.href = "login.html";
  } else {
    showToast("Registration failed (email may already exist)", "error");
  }
}



async function login() {
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  const res = await fetch(`${API_BASE_URL}/auth/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password })
  });

  if (!res.ok) {
    showToast("Invalid email or password", "error");
    return;
  }

  const data = await res.json();
  localStorage.setItem("token", data.token);

  // clear saved values
  clearFormValues(["login_email", "login_password"]);

  showToast("Login successful. Redirecting...", "success");

  setTimeout(() => {
    window.location.href = "dashboard.html";
  }, 1500);
}


function saveFormValue(key, value) {
  localStorage.setItem(key, value);
}

function getFormValue(key) {
  return localStorage.getItem(key) || "";
}

function clearFormValues(keys) {
  keys.forEach(k => localStorage.removeItem(k));
}

// Restore register form values on refresh
document.addEventListener("DOMContentLoaded", () => {
  const emailInput = document.getElementById("email");
  const passwordInput = document.getElementById("password");

  if (emailInput && passwordInput) {
    emailInput.value = getFormValue("register_email");
    passwordInput.value = getFormValue("register_password");
  }
});

// Restore login form values on refresh
document.addEventListener("DOMContentLoaded", () => {
  const emailInput = document.getElementById("email");
  const passwordInput = document.getElementById("password");

  if (emailInput && passwordInput) {
    emailInput.value = getFormValue("login_email");
    passwordInput.value = getFormValue("login_password");
  }
});