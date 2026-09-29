const API = "http://localhost:8080/api";

async function request(url, options={}) {
  const res = await fetch(url, {headers: {"Content-Type":"application/json"}, ...options});
  if (!res.ok) {
    const text = await res.text();
    throw new Error(text || "Request failed");
  }
  return res.status === 204 ? null : res.json();
}

async function loadAll() {
  try {
    const [students, rooms, complaints] = await Promise.all([
      request(`${API}/students`), request(`${API}/rooms`), request(`${API}/complaints`)
    ]);
    document.getElementById("apiStatus").textContent = "Backend Connected";
    document.getElementById("studentCount").textContent = students.length;
    document.getElementById("roomCount").textContent = rooms.length;
    document.getElementById("availableCount").textContent =
      rooms.reduce((sum,r)=>sum + Math.max(0, r.capacity-r.occupiedBeds),0);
    document.getElementById("complaintCount").textContent = complaints.length;

    document.getElementById("studentTable").innerHTML = students.map(s =>
      `<tr><td>${s.id}</td><td>${s.name}</td><td>${s.department}</td><td>${s.year}</td>
       <td>${s.roomNumber ?? "-"}</td>
       <td><button class="danger small" onclick="deleteStudent(${s.id})">Delete</button></td></tr>`).join("");

    document.getElementById("roomTable").innerHTML = rooms.map(r =>
      `<tr><td>${r.id}</td><td>${r.roomNumber}</td><td>${r.type}</td><td>${r.capacity}</td>
       <td>${r.occupiedBeds}</td><td>${Math.max(0,r.capacity-r.occupiedBeds)}</td></tr>`).join("");

    document.getElementById("complaintTable").innerHTML = complaints.map(c =>
      `<tr><td>${c.id}</td><td>${c.studentName ?? c.studentId}</td><td>${c.title}</td><td>${c.status}</td>
       <td>${c.status !== "RESOLVED" ? `<button class="small" onclick="resolveComplaint(${c.id})">Resolve</button>` : "Done"}</td></tr>`).join("");
  } catch(e) {
    document.getElementById("apiStatus").textContent = "Backend Not Connected";
    console.error(e);
  }
}

document.getElementById("studentForm").addEventListener("submit", async e => {
  e.preventDefault();
  try {
    await request(`${API}/students`, {method:"POST", body:JSON.stringify({
      name: studentName.value, email: studentEmail.value, phone: studentPhone.value,
      department: studentDept.value, year: Number(studentYear.value),
      roomId: studentRoom.value ? Number(studentRoom.value) : null
    })});
    e.target.reset(); await loadAll(); alert("Student added successfully");
  } catch(e){ alert("Error: " + e.message); }
});

document.getElementById("roomForm").addEventListener("submit", async e => {
  e.preventDefault();
  try {
    await request(`${API}/rooms`, {method:"POST", body:JSON.stringify({
      roomNumber: roomNumber.value, type: roomType.value, capacity: Number(roomCapacity.value)
    })});
    e.target.reset(); await loadAll(); alert("Room added successfully");
  } catch(e){ alert("Error: " + e.message); }
});

document.getElementById("complaintForm").addEventListener("submit", async e => {
  e.preventDefault();
  try {
    await request(`${API}/complaints`, {method:"POST", body:JSON.stringify({
      studentId: Number(complaintStudentId.value), title: complaintTitle.value,
      description: complaintDescription.value
    })});
    e.target.reset(); await loadAll(); alert("Complaint submitted");
  } catch(e){ alert("Error: " + e.message); }
});

async function deleteStudent(id){
  if(!confirm("Delete this student?")) return;
  try { await request(`${API}/students/${id}`, {method:"DELETE"}); await loadAll(); }
  catch(e){ alert("Error: " + e.message); }
}
async function resolveComplaint(id){
  try { await request(`${API}/complaints/${id}/resolve`, {method:"PUT"}); await loadAll(); }
  catch(e){ alert("Error: " + e.message); }
}
loadAll();
