import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEthnie } from 'app/shared/model/ethnie.model';
import { EthnieService } from './ethnie.service';

@Component({
  templateUrl: './ethnie-delete-dialog.component.html',
})
export class EthnieDeleteDialogComponent {
  ethnie?: IEthnie;

  constructor(protected ethnieService: EthnieService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ethnieService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ethnieListModification');
      this.activeModal.close();
    });
  }
}
