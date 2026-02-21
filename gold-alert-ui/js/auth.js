// ---------- AUTH HANDLERS ----------
async function register() {
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  if (!email || !password) {
    showToast("Email and password are required", "error");
    return;
  }

  if (!isValidEmail(email)) {
    showToast("Enter a valid email address", "error");
    return;
  }

  if (!isValidPassword(password)) {
    showToast("Password must be at least 6 characters", "error");
    return;
  }

  const confirmPassword = document.getElementById("confirmPassword").value.trim();
  if (password !== confirmPassword) {
    showToast("Passwords do not match", "error");
    return;
  }
  
  toggleRegisterLoading(true);

  try {
    const res = await fetch(`${API_BASE_URL}/auth/register`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password })
    });

    if (!res.ok) {
      showToast("Email already exists", "error");
      return;
    }

    showToast("Registration successful. Please login.", "success");

    setTimeout(() => {
      window.location.href = "login.html";
      // clear saved values
      clearFormValues(["register_email", "register_password"]);
    }, 1200);
  } catch (err) {
    showToast("Network error. Please try again.", "error");
  } finally {
    toggleRegisterLoading(false);
  }
}
// ---------- CONFIRM PASSWORD CHECK ----------
function onConfirmPasswordInput() {
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;
  const error = document.getElementById("confirmPasswordError");

  if (!confirmPassword) {
    error.classList.add("d-none");
    return;
  }

  if (password !== confirmPassword) {
    error.classList.remove("d-none");
  } else {
    error.classList.add("d-none");
  }
}

// ---------- UI HELPERS ----------
function toggleRegisterLoading(isLoading) {
  const btn = document.getElementById("registerBtn");
  const text = document.getElementById("registerBtnText");
  const spinner = document.getElementById("registerSpinner");

  btn.disabled = isLoading;
  text.classList.toggle("d-none", isLoading);
  spinner.classList.toggle("d-none", !isLoading);
}

// ---------- AUTH HANDLERS ----------
async function login() {
  const email = document.getElementById("email").value.trim();
  const password = document.getElementById("password").value.trim();

  // 🔐 Field validation
  if (!email || !password) {
    showToast("Email and password are required", "error");
    return;
  }

  if (!isValidEmail(email)) {
    showToast("Enter a valid email address", "error");
    return;
  }

  if (!isValidPassword(password)) {
    showToast("Password must be at least 6 characters", "error");
    return;
  }
  
  // 🔄 Disable button + show spinner
  toggleLoginLoading(true);

  try {
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

    showToast("Login successful", "success");

    setTimeout(() => {
      window.location.href = "dashboard.html";
      // clear saved values
      clearFormValues(["login_email", "login_password"]);
    }, 1000);
  } catch (err) {
    showToast("Network error. Please try again.", "error");
  } finally {
    toggleLoginLoading(false);
  }
}

// ---------- UI HELPERS ----------
function toggleLoginLoading(isLoading) {
  const btn = document.getElementById("loginBtn");
  const text = document.getElementById("loginBtnText");
  const spinner = document.getElementById("loginSpinner");

  btn.disabled = isLoading;
  text.classList.toggle("d-none", isLoading);
  spinner.classList.toggle("d-none", !isLoading);
}


// ---------- FORM VALUE PERSISTENCE ----------
function saveFormValue(key, value) {
  if (!value) {
    localStorage.removeItem(key);
  } else {
    localStorage.setItem(key, value);
  }
}

function getFormValue(key) {
  return localStorage.getItem(key) || "";
}

function clearFormValues(keys) {
  keys.forEach((k) => localStorage.removeItem(k));
}

// ---------- AUTO-FILL FORMS ON REFRESH ----------
document.addEventListener("DOMContentLoaded", () => {
  const page = window.location.pathname;

  // LOGIN PAGE
  if (page.includes("login")) {
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");

    if (emailInput) {
      emailInput.value = getFormValue("login_email");
    }

    if (passwordInput) {
      passwordInput.value = getFormValue("login_password");
    }
  }

  // REGISTER PAGE
  if (page.includes("register")) {
    const emailInput = document.getElementById("email");
    const passwordInput = document.getElementById("password");
    const confirmInput = document.getElementById("confirmPassword");

    if (emailInput) {
      emailInput.value = getFormValue("register_email");
    }

    if (passwordInput) {
      passwordInput.value = getFormValue("register_password");
    }

    if (confirmInput) {
      confirmInput.value = getFormValue("register_confirm_password");
      onConfirmPasswordInput(); // re-validate UI
    }
  }
});

// ---------- AUTH STATE HANDLER ----------
function updateNavbar() {
  const token = localStorage.getItem("token");

  const guestActions = document.getElementById("guestActions");
  const userActions = document.getElementById("userActions");
  const loggedUser = document.getElementById("loggedUser");

  if (!guestActions || !userActions) return;

  if (token) {
    guestActions.classList.add("d-none");
    userActions.classList.remove("d-none");

    if (loggedUser) {
      try {
        const payload = JSON.parse(atob(token.split(".")[1]));
        loggedUser.innerText = payload.sub || "User";
      } catch {
        loggedUser.innerText = "User";
      }
    }
  } else {
    guestActions.classList.remove("d-none");
    userActions.classList.add("d-none");
  }
}

// ---------- LOGIN FORM SUBMISSION HANDLER ----------
function handleLogin(event) {
  event.preventDefault(); // stop page reload
  login();                // call your existing login()
}

// ---------- REGISTER FORM SUBMISSION HANDLER ----------
function handleRegister(event) {
  event.preventDefault(); // stop page reload
  register();             // call your existing register()
}

// ---------- VALIDATION HELPERS ----------
function isValidEmail(email) {
  return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function isValidPassword(password) {
  return password.length >= 6;
}

// ---------- PASSWORD STRENGTH METER ----------
function checkPasswordStrength(password) {
  const bar = document.getElementById("passwordStrengthBar");
  const text = document.getElementById("passwordStrengthText");

  let strength = 0;
  if (password.length >= 6) strength++;
  if (/[A-Z]/.test(password)) strength++;
  if (/[0-9]/.test(password)) strength++;
  if (/[@$!%*?&]/.test(password)) strength++;

  const levels = [
    { width: "25%", color: "bg-danger", label: "Weak" },
    { width: "50%", color: "bg-warning", label: "Fair" },
    { width: "75%", color: "bg-info", label: "Good" },
    { width: "100%", color: "bg-success", label: "Strong" }
  ];

  if (!password) {
    bar.style.width = "0%";
    bar.className = "progress-bar";
    text.innerText = "";
    return;
  }

  const level = levels[strength - 1] || levels[0];
  bar.style.width = level.width;
  bar.className = `progress-bar ${level.color}`;
  text.innerText = level.label;
}

// ---------- HANDLE LOGIN RESET ----------
function handleLoginReset() {
  // Clear localStorage values for login
  clearFormValues(["login_email", "login_password"]);

  // Optional UX cleanup
  const email = document.getElementById("email");
  const password = document.getElementById("password");

  if (email) email.classList.remove("is-valid", "is-invalid");
  if (password) password.classList.remove("is-valid", "is-invalid");
}

// ---------- HANDLE REGISTER RESET ----------
function handleRegisterReset() {
  // Clear localStorage values for register
  clearFormValues([
    "register_email",
    "register_password",
    "register_confirm_password"
  ]);

  // Clear validation UI
  const confirmError = document.getElementById("confirmPasswordError");
  if (confirmError) confirmError.classList.add("d-none");

  const password = document.getElementById("password");
  const confirmPassword = document.getElementById("confirmPassword");

  if (password) password.classList.remove("is-valid", "is-invalid");
  if (confirmPassword) confirmPassword.classList.remove("is-valid", "is-invalid");
}