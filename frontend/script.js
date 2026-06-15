// =============================================
// DADOS DAS DIMENSÕES
// =============================================
const DIMENSIONS = [
  {
    badge: 'Dimensão 1',
    title: 'Acesso e Organização',
    questions: [
      { name: 'tempoEspera',      label: 'O tempo de espera para atendimento foi adequado.' },
      { name: 'facilAtendimento', label: 'Foi fácil conseguir atendimento nesta unidade.' },
    ],
  },
  {
    badge: 'Dimensão 2',
    title: 'Comunicação e Escuta',
    questions: [
      { name: 'ouvidoComAtencao',       label: 'Fui ouvido(a) com atenção pelos profissionais de saúde.' },
      { name: 'tempoExplicarProblema',  label: 'Tive tempo suficiente para explicar meu problema de saúde.' },
      { name: 'informacoesClarasSaude', label: 'Recebi informações claras sobre minha condição de saúde.' },
    ],
  },
  {
    badge: 'Dimensão 3',
    title: 'Vínculo e Confiança',
    questions: [
      { name: 'sentiOpiniaoConsiderada',  label: 'Senti que minha opinião foi considerada no atendimento.' },
      { name: 'tenhoConfiancaProfissionais', label: 'Tenho confiança na equipe de saúde desta unidade.' },
      { name: 'conhecemHistoricoSaude',   label: 'Fui atendido(a) por profissionais que conhecem meu histórico de saúde.' },
    ],
  },
  {
    badge: 'Dimensão 4',
    title: 'Resolutividade',
    questions: [
      { name: 'problemaResolvido',         label: 'Meu problema de saúde foi resolvido nesta unidade.' },
      { name: 'encaminhadoOutrosServicos', label: 'Quando necessário, fui encaminhado(a) adequadamente para outros serviços.' },
    ],
  },
  {
    badge: 'Dimensão 5',
    title: 'Humanização do Atendimento',
    questions: [
      { name: 'tratadoRespeito',     label: 'Fui tratado(a) com respeito e cordialidade.' },
      { name: 'empatiaAtendimento',  label: 'Os profissionais demonstraram empatia durante o atendimento.' },
      { name: 'sentiValorizado',     label: 'Me senti valorizado(a) durante o cuidado recebido.' },
      { name: 'sentiAcolhido',       label: 'O ambiente da unidade transmite acolhimento.' },
    ],
  },
  {
    badge: 'Dimensão 6',
    title: 'Continuidade do Cuidado',
    questions: [
      { name: 'continuidadeCuidado', label: 'Percebo continuidade no meu cuidado ao longo dos atendimentos.' },
      { name: 'sintoSeguro',         label: 'Sinto-me seguro(a) em retornar a esta unidade quando necessário.' },
    ],
  },
  {
    badge: 'Dimensão 7',
    title: 'Satisfação Geral',
    questions: [
      { name: 'satisfeitoAtendimento',   label: 'Estou satisfeito(a) com o atendimento recebido.' },
      { name: 'indicariaUnidade',        label: 'Indicaria esta unidade de saúde para outras pessoas.' },
      { name: 'continuarUsandoServicos', label: 'Pretendo continuar utilizando os serviços desta unidade.' },
    ],
  },
];

const EMOJIS = [
  { face: '😡', title: 'Muito insatisfeito' },
  { face: '😟', title: 'Insatisfeito' },
  { face: '😐', title: 'Neutro' },
  { face: '🙂', title: 'Satisfeito' },
  { face: '😄', title: 'Muito satisfeito' },
];

// =============================================
// RENDER
// =============================================
function renderLikert(name) {
  const options = EMOJIS.map((e, i) => `
    <label class="likert-item" title="${e.title}">
      <input type="radio" name="${name}" value="${i + 1}" ${i === 0 ? 'required' : ''}>
      <span class="likert-face" aria-hidden="true">${e.face}</span>
      <span class="likert-num">${i + 1}</span>
    </label>
  `).join('');

  return `
    <div class="likert" role="radiogroup" aria-label="Avaliação de 1 a 5 para: ${name}">
      ${options}
    </div>
    <div class="likert-legend" aria-hidden="true">
      <span>Discordo totalmente</span>
      <span>Concordo totalmente</span>
    </div>
  `;
}

function renderDimensions() {
  const container = document.getElementById('dimensoesContainer');
  container.innerHTML = DIMENSIONS.map(dim => `
    <section class="card">
      <div class="card-header">
        <span class="badge">${dim.badge}</span>
        <h2>${dim.title}</h2>
      </div>
      ${dim.questions.map(q => `
        <div class="field field--likert">
          <label>${q.label}</label>
          ${renderLikert(q.name)}
        </div>
      `).join('')}
    </section>
  `).join('');
}

// =============================================
// PROGRESS BAR
// =============================================
function updateProgress() {
  const form = document.getElementById('questionarioForm');

  // Non-radio required fields
  const nonRadio = [...form.querySelectorAll('[required]:not([type=radio])')];
  const filledNonRadio = nonRadio.filter(el => el.value.trim() !== '').length;

  // Radio groups (unique names among required radios)
  const radioGroups = [...new Set(
    [...form.querySelectorAll('input[type=radio][required]')].map(r => r.name)
  )];
  const filledRadios = radioGroups.filter(name =>
    form.querySelector(`input[name="${name}"]:checked`)
  ).length;

  const total = nonRadio.length + radioGroups.length;
  const done  = filledNonRadio + filledRadios;
  const pct   = total > 0 ? Math.round((done / total) * 100) : 0;

  document.getElementById('progressFill').style.width = pct + '%';
  document.getElementById('progressBar').setAttribute('aria-valuenow', pct);
  document.getElementById('progressText').textContent = `${pct}% preenchido`;
}

// =============================================
// JS FALLBACK FOR :has() — marks .selected
// =============================================
function setupLikertFallback() {
  document.getElementById('dimensoesContainer').addEventListener('change', e => {
    if (e.target.type !== 'radio') return;
    const group = document.querySelectorAll(`input[name="${e.target.name}"]`);
    group.forEach(radio => radio.closest('.likert-item').classList.remove('selected'));
    e.target.closest('.likert-item').classList.add('selected');
  });
}

// =============================================
// DYNAMIC VISIBILITY
// =============================================
function setupDynamicFields() {
  const toggle = (selectId, divId, triggerValue) => {
    const sel = document.getElementById(selectId);
    const div = document.getElementById(divId);
    if (!sel || !div) return;
    sel.addEventListener('change', () =>
      div.classList.toggle('hidden', sel.value !== triggerValue)
    );
  };

  toggle('possuiDeficiencia',       'divDeficienciaEspecifica', 'sim');
  toggle('identidadeGenero',        'divPrefiroAutodeclarar',   'prefiroAutodeclarar');
  toggle('tipoAtendimentoRecebido', 'divOutroAtendimento',      'outro');
}

// =============================================
// FORM SUBMIT
// =============================================
const NUMERIC_FIELDS = DIMENSIONS.flatMap(d => d.questions.map(q => q.name));

function setupSubmit() {
  document.getElementById('questionarioForm').addEventListener('submit', function (e) {
    e.preventDefault();

    // Find first invalid field and scroll to it
    const firstInvalid = this.querySelector(':invalid');
    if (firstInvalid) {
      firstInvalid.closest('.field')?.classList.add('field--error');
      firstInvalid.scrollIntoView({ behavior: 'smooth', block: 'center' });
      return;
    }

    const btn = document.getElementById('btnSubmit');
    btn.disabled = true;
    btn.innerHTML = '<span>Enviando...</span>';

    const dados = Object.fromEntries(new FormData(this).entries());
    dados.possuiDeficiencia = dados.possuiDeficiencia === 'sim';
    NUMERIC_FIELDS.forEach(f => { if (dados[f] !== undefined) dados[f] = Number(dados[f]); });

    console.log('Payload:', JSON.stringify(dados, null, 2));

    fetch('https://projeto-questionario-aps.onrender.com/api/questionario', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(dados),
    })
      .then(res => {
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        showSuccess();
      })
      .catch(err => {
        console.error('Erro de envio:', err);
        btn.disabled = false;
        btn.innerHTML = `
          <span>Enviar Avaliação</span>
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none"
            viewBox="0 0 24 24" stroke="currentColor" stroke-width="2.5" aria-hidden="true">
            <path stroke-linecap="round" stroke-linejoin="round" d="M13 7l5 5m0 0l-5 5m5-5H6"/>
          </svg>`;
        showToast('Não foi possível enviar. Verifique sua conexão e tente novamente.');
      });
  });

  // Remove error state on change
  document.getElementById('questionarioForm').addEventListener('change', e => {
    e.target.closest('.field')?.classList.remove('field--error');
  });
}

function showSuccess() {
  const form = document.getElementById('questionarioForm');
  form.innerHTML = `
    <section class="card">
      <div class="result-card">
        <span class="result-icon">✅</span>
        <h2>Avaliação enviada com sucesso!</h2>
        <p>Obrigado pela sua contribuição. Seu feedback é essencial para melhorarmos os serviços de saúde em Rondônia.</p>
      </div>
    </section>
  `;
  window.scrollTo({ top: 0, behavior: 'smooth' });

  // Hide progress bar
  document.querySelector('.progress-wrap').style.opacity = '0';
}

function showToast(msg) {
  document.querySelector('.toast')?.remove();
  const t = document.createElement('div');
  t.className = 'toast';
  t.textContent = msg;
  document.body.appendChild(t);
  setTimeout(() => t.remove(), 4500);
}

// =============================================
// INIT
// =============================================
document.addEventListener('DOMContentLoaded', () => {
  renderDimensions();
  setupDynamicFields();
  setupLikertFallback();
  setupSubmit();

  const form = document.getElementById('questionarioForm');
  form.addEventListener('change', updateProgress);
  form.addEventListener('input',  updateProgress);
  updateProgress();
});