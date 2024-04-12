window.addEventListener("load", function(){

    // 검색을 위한 부모 form 찾기
    const searchBookForm = this.document.querySelector("#search-book-form");
    
    // 쿼리 검색
    const searchQueryInput = searchBookForm.querySelector(".search-query-input");
    const queryInputBtn = searchBookForm.querySelector(".query-input-btn");
    
    // 카테고리 검색
    const searchCategory = searchBookForm.querySelector(".search-category");

    // 검색 결과 list를 뿌려줄 컨텐츠 ul ( li로 리스트가 뿌려짐 )
    const contentUl = this.document.querySelector(".content-ul");
    
    //  쿼리 검색시에도 카테고리 선택값이 필요하므로 전역 변수 생성
    let categoryValue;
 

    // ===================================== 쿼리로 검색하는 경우 =======================================
    // =================================================================================================

    // 쿼리 입력 후 검색 버튼 클릭 or 엔터 클릭 시 실행
    queryInputBtn.addEventListener("click", async function(e){
        e.preventDefault();

        // 사용자가 검색을 눌렀을때 검색어
        let queryValue = searchQueryInput.value;

        // 쿼리 검색 시 카테고리 선택 없는 경우 undefined 값처리
        if(categoryValue === undefined)
            categoryValue = null;
        
        // fetch 통신 후 response 받는 절
        let response = await getByParams(queryValue, categoryValue);

        // 서버에서 문제가 생긴 경우 return
        if(response.status != 200){
            contentUl.innerHTML= '';
            contentUl.innerHTML= `검색 결과가 없습니다 ...  유감.`;
            return;
        }

        let list = await response.json();
        await printBookList(list);
        
    });


    // =================================== 카테고리로 검색하는 경우 =======================================
    // ==================================================================================================
    // 카테고리 클릭 시 실행
    searchCategory.addEventListener("click", async function(e){

        // 카테고리로 검색하는 경우
        let clickedCategoryLi = e.target;

        // 클릭한 카테고리가 li가 아닌 경우 모두 퇴출
        if(clickedCategoryLi.tagName != "LI")
            return;

        // 모든 카테고리 li에서 br-color:main-5를 제거해주기 위해 SelecetorAll
        let allLis = searchCategory.querySelectorAll("li");
        
        
        for(let a of allLis){
            // 모든 카테고리 li와 span의 style 원래대로 돌려놓기
            // 기본스타일로 변경해준다.
            a.classList.remove("bg-color:main-5");
            a.classList.add("bg-color:main-1");
            a.querySelector("span").classList.remove("color:base-1");
        }
        
        // 클릭된 li태그와 span에 색상 주기
        // 클릭이벤트 발생한 li에서 클래스를 넣어주기 위한 span 태그 찾기
        let categorySpan = clickedCategoryLi.querySelector("span");
        categorySpan.classList.add("color:base-1");
        clickedCategoryLi.classList.remove("bg-color:main-1");
        clickedCategoryLi.classList.add("bg-color:main-5");

        // 클릭한 li span 속 dataset value 찾기
        categoryValue = categorySpan.dataset.id;
        let queryValue = searchQueryInput.value;

        //비동기 fetch 메소드 호출 및 GET 통신
        let response = await getByParams(queryValue, categoryValue);

        // server에서 문제 생긴 경우 return
        if(response.status != 200){
            contentUl.innerHTML= '';
            contentUl.innerHTML= `검색 결과가 없습니다 ...  유감.`;
            return;
        }


        let list = await response.json();
        await printBookList(list);
        
    })
    
    // ================================= 파라미터 추가하여 서버와 통신  =======================================
    // ======================================================================================================
    function getByParams(q, c){
        
        const url = `/api/book/list?q=${q}&c=${c}`;
        // const method = "GET";
        return fetch(url);
    }

    // ====================================== 검색결과 list를 출력 ===========================================
    // =====================================================================================================
    function printBookList(list){
        contentUl.innerHTML= '';

        // 리스트는 검색결과가 없는 경우에  null이 아니라 length 0으로 나옴
        if(list.length <= 0){
            contentUl.innerHTML= '';
            contentUl.innerHTML= `검색 결과가 없습니다 ...  유감.`;
            return;
        }

        list.forEach(n => {
                
            let liHTML = ` <li
            class="jc:center ai:center p:3"
        >
            <div
                class="gap:4 d:flex fl-dir:column lg:ai:center"
            >
                <div class="w:100p lg:w:3 d:flex jc:center">
                    <a
                        href="@{./detail(id=${n.id})}"
                    >
                        <img
                            src="${n.cover}"
                            class="h:3 margin-right:auto"
                            alt="도둑맞은 집중력.img"
                        />
                    </a>
                </div>

                <div class="w:100p d: fl-dir:column ai:center">
                    <span></span>
                    <h1 class="fs:3 fw:3">
                        <a
                            
                            href="@{./detail(id=${n.id})}"
                            >${n.title}</a
                        >
                    </h1>
                    <div
                        class="fs:2 fw:1 mt:1"
                    >
                        <a
                            href="@{./detail(id=${n.id})}"
                            >${n.author}</a
                        >
                    </div>
                    <div class="mt:1 fs:1">
                        <div>
                            <a
                                href="@{./detail(id=${n.id})}"
                                >${n.publisher}</a
                            >
                        </div>
                        <div>
                            <a
                                href="@{./detail(id=${n.id})}"
                                >${n.pubDate}</a
                            >
                        </div>
                    </div>
                    <div
                        class="description d:none lg:d:block mt:2 w:10p"
                    >${n.description}
                    </div>
                </div>
            </div>
        </li>
            `;
            contentUl.insertAdjacentHTML("beforeend", liHTML);
        });
    }







})