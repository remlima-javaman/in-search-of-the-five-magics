document.addEventListener('DOMContentLoaded', (event) => {
    const jsonDataElement = document.getElementById('jsonData');
    const jsonData = [JSON.parse(jsonDataElement.getAttribute('data-json'))];
    const root = document.getElementById("root");
    const assetsJson = {};
    for (let i = 0; i < jsonData.length; i++) {
        const conversation = jsonData[i];
        const messages = getConversationMessages(jsonData[i]);
        const div = document.createElement("div");
        div.className = "conversation";
        div.innerHTML = "<h4>" + conversation.title + "</h4>";
        for (let j = 0; j < messages.length; j++) {
            const message = document.createElement("pre");
            message.className = "message";
            let nameAuthor = messages[j].author
            if (nameAuthor === "user")
                nameAuthor = "Glitch";
            message.innerHTML = `<div class="author">${nameAuthor}</div>`
            if (messages[j].parts) {
                for (let k = 0; k < messages[j].parts.length; k++) {
                    const part = messages[j].parts[k];
                    if (part.text) {
                        message.innerHTML += `<div>${part.text}</div>`;
                    } else if (assetsJson) {
                        if (part.transcript) {
                            message.innerHTML += `<div>[Transcript]: ${part.transcript}</div>`;
                        } else if (part.asset) {
                            const link = assetsJson[part.asset.asset_pointer];
                            if (link) {
                                message.innerHTML += `<div>[File]: <a href="${link}">${link}</a></div>`;
                            } else {
                                message.innerHTML += `<div>[File]: -Deleted-</div>`;
                            }
                        }
                    }
                }
            }
            div.appendChild(message);
        }
        root.appendChild(div);
    }

});

function getConversationMessages(conversation) {
    const messages = [];
    let currentNode = conversation.current_node;
    while (currentNode != null) {
        const node = conversation.mapping[currentNode];
        if (node.message &&
            node.message.content &&
            node.message.content.parts &&
            node.message.content.parts.length > 0 &&
            (node.message.author.role !== "system" || node.message.metadata.is_user_system_message)) {

            author = node.message.author.role;

            if (author === "assistant" || author === "tool") {
                author = node.message.author.name;
            } else if (author === "system" && node.message.metadata.is_user_system_message) {
                author = (node.message.author.name != null ? "TIO_SAM_REBASE_MODE_ON" : node.message.author.name);
            }

            if (node.message.content.content_type === "text" || node.message.content.content_type === "multimodal_text") {
                const parts = [];
                for (let i = 0; i < node.message.content.parts.length; i++) {
                    const part = node.message.content.parts[i];
                    if (typeof part === "string" && part.length > 0) {
                        parts.push({text: part});
                    } else if (part.content_type === "audio_transcription") {
                        parts.push({transcript: part.text});
                    } else if (part.content_type === "audio_asset_pointer" || part.content_type === "image_asset_pointer" || part.content_type === "video_container_asset_pointer") {
                        parts.push({asset: part});
                    } else if (part.content_type === "real_time_user_audio_video_asset_pointer") {
                        if (part.audio_asset_pointer) {
                            parts.push({asset: part.audio_asset_pointer});
                        }
                        if (part.video_container_asset_pointer) {
                            parts.push({asset: part.video_container_asset_pointer});
                        }
                        for (let j = 0; j < part.frames_asset_pointers.length; j++) {
                            parts.push({asset: part.frames_asset_pointers[j]});
                        }
                    }
                }
                if (parts.length > 0) {
                    messages.push({author, parts: parts});
                }
            }
        }
        currentNode = node.parent;
    }
    return messages.reverse();
}

function liberarLogs(sempreSim) {
    const jsonDataElement = document.getElementById('jsonData');
    const jsonData = [JSON.parse(jsonDataElement.getAttribute('data-json'))];

    const rest = sempreSim.value;
    if (rest === "Sim") { //Yes, we will show!
        try {
            document.getElementById("jsonContent").textContent = JSON.stringify(jsonData, null, 2);
            document.getElementById("jsonDiv").style.display = "block";
        } catch (e) {
            console.error("Erro ao formatar JSON:", e);
        }
    } else {
        try {
            document.getElementById("jsonContent").textContent = JSON.stringify(jsonData, null, 2);
            document.getElementById("jsonDiv").style.display = "block";
        } catch (e) {
            console.error("Erro ao formatar JSON:", e);
        }
        const divTroll = document.getElementById("id-troll");
        divTroll.style.display = "block";
    }

}

